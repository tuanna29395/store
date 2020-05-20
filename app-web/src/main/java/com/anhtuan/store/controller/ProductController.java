package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.dto.request.ProductSearchRqDto;
import com.anhtuan.store.dto.request.ToppingReq;
import com.anhtuan.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute(ModelViewConst.Carts.TOPPING, new ToppingReq());
        return ViewHtmlConst.Products.LIST;
    }

    @GetMapping(value = "/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute(ModelViewConst.Product.PRODUCT_DETAIL, productService.findById(id));
        model.addAttribute(ModelViewConst.Carts.TOPPING, new ToppingReq());

        return ViewHtmlConst.Products.DETAIL;

    }
}
