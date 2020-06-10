package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.EndPointConst;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.dto.request.ForgotPasswordTemplateDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.PasswordResetTokenEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.PasswordResetTokenRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.PasswordResetTokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    private static final long passwordResetTokenExpired = 10 * 60 * 1000;

    @Override
    public ForgotPasswordTemplateDto createForgotPasswordTemplateDto(String email, String baseUrl) {
        UserEntity userEntity = userRepository.findByEmailAndDeletedFlag(email, DeleteFlag.NOT_DELETE.getVal())
                .orElseThrow(() -> Exception.badRequest().build("email does not exist"));

        String token = DigestUtils.md5Hex(email + userEntity.getPassword());
        String resetPasswordUrl = baseUrl + EndPointConst.Password.RESET_PASSWORD_URL + token;

        PasswordResetTokenEntity tokenEntity = tokenRepository.findByUserId(userEntity.getId());
        if (Objects.nonNull(tokenEntity)) {
            tokenEntity.setToken(token);
        } else {
            tokenEntity = new PasswordResetTokenEntity();
            tokenEntity.setToken(token);
            tokenEntity.setUser(userEntity);
        }

        tokenEntity.setExpiryDate(new Timestamp(System.currentTimeMillis() + passwordResetTokenExpired));
        tokenRepository.save(tokenEntity);

        ForgotPasswordTemplateDto forgotPasswordTemplateDto = new ForgotPasswordTemplateDto();
        forgotPasswordTemplateDto.setEmail(email);
        forgotPasswordTemplateDto.setResetPasswordUrl(resetPasswordUrl);
        return forgotPasswordTemplateDto;
    }

    @Override
    public PasswordResetTokenEntity checkToken(String token) {
        if (token.isEmpty()) {
            throw Exception.badRequest().build("token invalid", 400);
        }

        PasswordResetTokenEntity tokenEntity = passwordResetTokenRepository.findByToken(token);
        if (!Objects.nonNull(tokenEntity)) throw Exception.badRequest().build("token invalid", 400);

        if (tokenEntity.getExpiryDate().before(new Timestamp(System.currentTimeMillis()))) {
            throw Exception.badRequest().build("token has expired", 400);
        }

        return tokenEntity;
    }
}
