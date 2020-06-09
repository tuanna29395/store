package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.constants.EndPointConst;
import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.dto.request.ForgotPasswordTemplateDto;
import com.anhtuan.store.dto.request.PasswordForgotDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.PasswordResetTokenEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.PasswordResetTokenRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.EmailService;
import com.anhtuan.store.service.UserService;
import com.anhtuan.store.support.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Objects;

@Controller
@RequestMapping("/password")
public class PasswordForgotController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    private static final long passwordResetTokenExpired = 5 * 60 * 1000;

    @GetMapping("/forgot")
    public String displayForgotPasswordPage(@ModelAttribute(ModelViewConst.Password.PASSWORD_FORGOT_DTO) PasswordForgotDto passwordForgotDto, Model model) {
        return ViewHtmlConst.Password.FORGOT_PASSWORD;
    }

    @PostMapping("/forgot")
    public String submitForgotPassword(HttpServletRequest request,
                                       @Valid @ModelAttribute(ModelViewConst.Password.PASSWORD_FORGOT_DTO) PasswordForgotDto passwordForgotDto,
                                       BindingResult bindingResult, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            return ViewHtmlConst.Password.FORGOT_PASSWORD;
        }
        try {
            String email = passwordForgotDto.getEmail();
            ForgotPasswordTemplateDto templateDto = createForgotPasswordTemplateDto(email, getBaseUrl(request));
            emailService.sendEmailForgotPassword(email, Commons.SUBJECT_MAIL_FORGOT_PASSWORD, templateDto.getResetPasswordUrl());
        } catch (Exception e) {
            ra.addFlashAttribute(ModelViewConst.Password.PASSWORD_FORGOT_DTO, passwordForgotDto);
            String message = (e instanceof Exception) ? ((Exception) e).getErrorObject().getMessage() : "password reset failed";
            MessageHelper.addErrorAttribute(ra, message);
            return ViewHtmlConst.Password.REDIRECT_FORGOT_PASSWORD;
        }

        MessageHelper.addSuccessAttribute(ra, "please check mail box");
        return redirect(EndPointConst.LOGIN);
    }

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
}
