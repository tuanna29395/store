package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.dto.request.ProductSearchRqDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import com.anhtuan.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProduct(ProductSearchRqDto searchRqDto, Pageable pageable, Model model) {
        model.addAttribute(ModelViewConst.Product.PRODUCT_PAGEABLE, productService.search(searchRqDto, pageable));
        model.addAttribute(ModelViewConst.Product.SEARCH_DTO, searchRqDto);

        return ViewHtmlConst.Products.LIST;
    }
}
