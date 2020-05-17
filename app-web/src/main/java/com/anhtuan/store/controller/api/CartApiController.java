package com.anhtuan.store.controller.api;

import com.anhtuan.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/cart")
public class CartApiController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<?> getAll(HttpSession session) {
        return ResponseEntity.ok(cartService.getAll(session));
    }
}
