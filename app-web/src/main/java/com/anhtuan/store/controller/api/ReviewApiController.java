package com.anhtuan.store.controller.api;

import com.anhtuan.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/review")
public class ReviewApiController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{productId}/reviews")
    public ResponseEntity<?> reviews(@PathVariable(name = "productId") Integer productId, @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(productService.listReview(productId, pageable));
    }
}
