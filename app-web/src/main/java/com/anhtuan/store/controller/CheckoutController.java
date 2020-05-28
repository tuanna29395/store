package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.config.Principal;
import com.anhtuan.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String showPage(@AuthenticationPrincipal Principal principal, Model model, HttpSession session) {
        model.addAttribute(ModelViewConst.checkouts.USER_INFO, principal);
        model.addAttribute(ModelViewConst.checkouts.CART_INFO, cartService.getAll(session));
        model.addAttribute(ModelViewConst.checkouts.TOTAL_CART, cartService.totalCart(session));
        return ViewHtmlConst.Checkout.CHECKOUT;
    }
}
