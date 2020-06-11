package com.anhtuan.store.controller.admin;

import com.anhtuan.store.commons.constants.EndPointConst;
import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.controller.BaseController;
import com.anhtuan.store.dto.request.CategoryAddDto;
import com.anhtuan.store.dto.request.CategorySearchDto;
import com.anhtuan.store.dto.response.CategoryResponseDto;
import com.anhtuan.store.model.CategoryEntity;
import com.anhtuan.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.CacheResponse;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute(ModelViewConst.Category.CATEGORY_PAGEABLE, categoryService.getAll());
        return ViewHtmlConst.AdminCategory.List;
    }

    @GetMapping("/add")
    public String showPageCreate(Model model) {
        model.addAttribute(ModelViewConst.Category.CATEGORY_DTO, new CategoryAddDto());
        return ViewHtmlConst.AdminCategory.CREATE;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submitAddCategory(Model model, @Valid @ModelAttribute(ModelViewConst.Category.CATEGORY_DTO) CategoryAddDto categoryAddDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelViewConst.Category.CATEGORY_DTO, new CategoryAddDto());
            return ViewHtmlConst.AdminCategory.CREATE;
        }
        categoryService.add(categoryAddDto);
        return redirect(EndPointConst.Category.ADMIN_LIST);
    }

    @GetMapping("/{id}/edit")
    public String showPageEdit(Model model, @PathVariable Integer id) {
        model.addAttribute(ModelViewConst.Category.CATEGORY_DTO, categoryService.findById(id));
        return ViewHtmlConst.AdminCategory.EDIT;
    }

    @PostMapping("/{id}/edit")
    public String submitEdit(Model model, @PathVariable Integer id, @Valid @ModelAttribute(ModelViewConst.Category.CATEGORY_DTO) CategoryAddDto categoryAddDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelViewConst.Category.CATEGORY_DTO, categoryAddDto);
            return ViewHtmlConst.AdminCategory.EDIT;
        }
        categoryService.edit(categoryAddDto, id);
        return redirect(EndPointConst.Category.ADMIN_LIST);
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return redirect(EndPointConst.Category.ADMIN_LIST);
    }


}
