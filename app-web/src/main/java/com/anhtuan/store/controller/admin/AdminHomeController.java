package com.anhtuan.store.controller.admin;

import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.dto.request.UserRegisterRqDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {
    @GetMapping("/admin")
    public String home() {
        return "admin-index";
    }

    @RequestMapping("/admin/login")
    public String login(Model model) {
        model.addAttribute(ModelViewConst.User.USER_REGISTER_DTO, new UserRegisterRqDto());
        return "admin-login";
    }

    @GetMapping("/admin/login-error")
    public String loginError(Model model) {
        model.addAttribute(ModelViewConst.User.USER_REGISTER_DTO, new UserRegisterRqDto());
        model.addAttribute("loginError", true);
        return "admin-login";
    }
}
