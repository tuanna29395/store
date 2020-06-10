package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.Role;
import com.anhtuan.store.dto.request.PasswordResetDto;
import com.anhtuan.store.dto.request.UserRegisterRqDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.PasswordResetTokenEntity;
import com.anhtuan.store.model.RoleEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.PasswordResetTokenRepository;
import com.anhtuan.store.repository.RoleRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public void registerUser(UserRegisterRqDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity user = modelMapper.map(dto, UserEntity.class);
        RoleEntity role = roleRepository.findByIdAndDeleteFlagIsNull(Role.CUSTOMER.getVal()).orElseThrow(() -> Exception.dataNotFound()
                .build(ErrorMessage.Role.ROLE_NOT_FOUND
                        .replace(Commons.ID, String.valueOf(Role.CUSTOMER.getVal()))));
        user.setRole(role);
        user.setDeletedFlag(DeleteFlag.NOT_DELETE.getVal());

        userRepository.save(user);
    }

    @Override
    public void resetPassword(PasswordResetDto passwordResetDto, UserEntity userEntity) {
        PasswordResetTokenEntity tokenEntity = passwordResetTokenRepository.findByToken(passwordResetDto.getToken());
        passwordResetTokenRepository.delete(tokenEntity);
        userEntity.setPassword(passwordEncoder.encode(passwordResetDto.getConfirmPassword()));
        userRepository.save(userEntity);
    }
}
