package com.anhtuan.store.controller;

import com.anhtuan.store.dto.request.PasswordResetDto;
import com.anhtuan.store.model.PasswordResetTokenEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.PasswordResetTokenRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/reset-password")
public class PasswordResetController {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordReset() {
        return new PasswordResetDto();
    }

    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        PasswordResetTokenEntity resetToken = tokenRepository.findByToken(token);
        PasswordResetDto passwordResetDto = new PasswordResetDto();
        if (resetToken == null) {
            model.addAttribute("error", "Could not find password reset token.");
            model.addAttribute("passwordResetForm", passwordResetDto);
        } else if (resetToken.isExpired()) {
            model.addAttribute("error", "Token has expired, please request a new password reset.");
            model.addAttribute("passwordResetForm", passwordResetDto);
        } else {

            passwordResetDto.setToken(resetToken.getToken());
            model.addAttribute("passwordResetForm", passwordResetDto);
        }

        return "password/reset";
    }

    @PostMapping
    @Transactional
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/reset-password?token=" + form.getToken();
        }

        PasswordResetTokenEntity token = tokenRepository.findByToken(form.getToken());
        UserEntity user = token.getUser();
        String updatedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(updatedPassword);
        userRepository.save(user);
        tokenRepository.delete(token);

        return "redirect:/login";
    }
}
