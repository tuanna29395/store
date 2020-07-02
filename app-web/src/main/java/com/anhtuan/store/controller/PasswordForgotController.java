package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.constants.EndPointConst;
import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.dto.request.ForgotPasswordTemplateDto;
import com.anhtuan.store.dto.request.PasswordForgotDto;
import com.anhtuan.store.dto.request.PasswordResetDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.PasswordResetTokenEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.service.EmailService;
import com.anhtuan.store.service.PasswordResetTokenService;
import com.anhtuan.store.service.UserService;
import com.anhtuan.store.support.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/password")
public class PasswordForgotController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenService passwordService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


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
            ForgotPasswordTemplateDto templateDto = passwordService.createForgotPasswordTemplateDto(email, getBaseUrl(request));
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

    @GetMapping("/reset")
    public String showResetPasswordPage(Model model, @RequestParam("token") String token) {
        passwordService.checkToken(token);
        PasswordResetDto passwordResetDto = new PasswordResetDto();
        passwordResetDto.setToken(token);
        model.addAttribute(ModelViewConst.Password.PASSWORD_RESET_DTO, passwordResetDto);
        return ViewHtmlConst.Password.RESET_PASSWORD;
    }

    @PostMapping("/reset")
    public String submitRestPassword(RedirectAttributes ra,
                                     @Valid @ModelAttribute(ModelViewConst.Password.PASSWORD_RESET_DTO) PasswordResetDto passwordResetDto,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ViewHtmlConst.Password.RESET_PASSWORD;
        }

        PasswordResetTokenEntity tokenEntity = passwordService.checkToken(passwordResetDto.getToken());
        userService.resetPassword(passwordResetDto, tokenEntity.getUser());
        MessageHelper.addSuccessAttribute(ra, "reset password success");
        return redirect(EndPointConst.LOGIN);
    }

}
