package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.ViewHtmlConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutController {
    @GetMapping
    public String showPage() {
        return ViewHtmlConst.Checkout.CHECKOUT;
    }
}
