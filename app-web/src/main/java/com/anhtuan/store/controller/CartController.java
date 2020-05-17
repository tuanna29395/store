package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.dto.request.ToppingReq;
import com.anhtuan.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping(value = "/add/{productId}")
    public String addProduct(Model model, HttpSession session, @PathVariable("productId") Integer productId,ToppingReq toppingReq) {
        session.setAttribute("myCartItems", cartService.addProductToCart(session, productId, toppingReq));
        return ViewHtmlConst.Carts.LIST;
    }
}
