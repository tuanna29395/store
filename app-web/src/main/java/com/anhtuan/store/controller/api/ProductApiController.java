package com.anhtuan.store.controller.api;

import com.anhtuan.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductApiController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/maxPrice")
    public ResponseEntity<?> getMaxPrice() {
        return ResponseEntity.ok(productService.getMaxPrice());
    }

    @GetMapping(value = "/minPrice")
    public ResponseEntity<?> getMinPrice() {
        return ResponseEntity.ok(productService.getMinPrice());
    }

}
