package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.*;
import com.anhtuan.store.dto.request.UserRegisterRqDto;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.UserService;
import com.anhtuan.store.support.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserController extends BaseController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String signUp(@ModelAttribute(ModelViewConst.User.USER_REGISTER_DTO) @Valid UserRegisterRqDto user, BindingResult bindingResult, RedirectAttributes ra, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelViewConst.User.USER_REGISTER_DTO, user);
            return ViewHtmlConst.Users.SIGN_UP_SIGN_IN;
        }

        if (userRepository.existsByEmailIgnoreCaseAndDeletedFlagIsNull(user.getEmail())) {
            MessageHelper.addErrorAttribute(ra, String.format(ErrorMessage.DUPLICATED_DATA, Messages.User.EMAIL));
            return redirect(EndPointConst.LOGIN);
        }

        userService.registerUser(user);
        MessageHelper.addSuccessAttribute(ra, String.format(Messages.REGISTER_SUCCESS, Messages.User.USER));

        return redirect(EndPointConst.LOGIN);
    }

    @GetMapping("/profile")
    public String profile(Model model){
        return ViewHtmlConst.Users.USER_PROFILE;
    }

}
