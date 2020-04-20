package com.anhtuan.store.controller.api;

import com.anhtuan.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }
}
