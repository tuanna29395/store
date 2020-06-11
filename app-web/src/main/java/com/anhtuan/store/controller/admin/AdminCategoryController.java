package com.anhtuan.store.controller.admin;

import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.dto.request.CategorySearchDto;
import com.anhtuan.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String list(Model model, @ModelAttribute(ModelViewConst.Category.CATEGORY_SEARCH) CategorySearchDto searchDto, Pageable pageable) {
        model.addAttribute(ModelViewConst.Category.CATEGORY_PAGEABLE, categoryService.search(searchDto, pageable));
        return ViewHtmlConst.AdminCategory.List;
    }
}
