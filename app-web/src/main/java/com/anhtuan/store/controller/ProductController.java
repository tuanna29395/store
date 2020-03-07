package com.anhtuan.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    @GetMapping
    public String listProduct() {
        return "/products/list-product.html";
    }
}
