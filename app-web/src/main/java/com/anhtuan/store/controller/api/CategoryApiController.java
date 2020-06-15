package com.anhtuan.store.controller.api;

import com.anhtuan.store.dto.request.CategoryChangeStatusDto;
import com.anhtuan.store.dto.request.CategorySearchDto;
import com.anhtuan.store.repository.CategoryRepository;
import com.anhtuan.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryService.getAll(new CategorySearchDto()));
    }

    @PostMapping("/{id}/change-status")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> changeStatus(@PathVariable("id") Integer categoryId, @RequestBody CategoryChangeStatusDto changeStatusDto) {
        categoryService.updateEnableFlag(categoryId, changeStatusDto.getIsEnabled());
        return ResponseEntity.ok("");
    }
}
