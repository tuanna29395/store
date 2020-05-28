package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.dto.request.UserRegisterRqDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, HttpSession session) {
        model.addAttribute(ModelViewConst.User.USER_REGISTER_DTO, new UserRegisterRqDto());
//        String referrer = request.getHeader("Referer");
//        if (!referrer.contains("login")){
//            session.setAttribute(Commons.SESSION_URL_PRIOR_LOGIN, referrer);
//        }
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
