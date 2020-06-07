package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.EndPointConst;
import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.ReviewReqDto;
import com.anhtuan.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/review")
public class ReviewController extends BaseController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/{productId}/add")
    public String addReview(@AuthenticationPrincipal Principal principal, @ModelAttribute("reviewDto") ReviewReqDto reqDto, @PathVariable(name = "productId") Integer productId) {
        productService.addReview(principal, reqDto, productId);
        return redirect("/products/" + productId);

    }
}
