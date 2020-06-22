package com.anhtuan.store.controller.api;

import com.anhtuan.store.dto.request.CategoryChangeStatusDto;
import com.anhtuan.store.dto.request.ProductAddEditDto;
import com.anhtuan.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductApiController {
    public final Integer NUMBER_PRODUCT_BEST_SELLING = 3;

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

    @PostMapping("/{id}/change-status")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> changeStatus(@PathVariable("id") Integer productId, @RequestBody ProductAddEditDto productAddEditDto) {
        productService.updateStatus(productId, productAddEditDto);
        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/best-selling")
    public ResponseEntity<?> listBestSelling() {
        return ResponseEntity.ok(productService.litProductBestSelling(NUMBER_PRODUCT_BEST_SELLING));
    }

}
