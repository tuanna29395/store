package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.dto.request.UserRegisterRqDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(ModelViewConst.User.USER_REGISTER_DTO, new UserRegisterRqDto());
        return ViewHtmlConst.Users.SIGN_UP_SIGN_IN;
    }

    @GetMapping("/index")
    public String error() {
        return "index";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute(ModelViewConst.User.USER_REGISTER_DTO, new UserRegisterRqDto());
        model.addAttribute("loginError", true);
        return ViewHtmlConst.Users.SIGN_UP_SIGN_IN;
    }

}
