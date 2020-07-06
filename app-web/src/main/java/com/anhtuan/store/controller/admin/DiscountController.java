package com.anhtuan.store.controller.admin;

import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.controller.BaseController;
import com.anhtuan.store.dto.request.DiscountRequestAddDto;
import com.anhtuan.store.dto.request.DiscountRequestSearchDto;
import com.anhtuan.store.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin/discounts")
public class DiscountController extends BaseController {

    @Autowired
    private DiscountService discountService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // change the format according to your need.
        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping
    public String showPageList(Model model, Pageable pageable, @ModelAttribute(ModelViewConst.Discount.DISCOUNT_SEARCH_DTO) DiscountRequestSearchDto dto) {
        model.addAttribute(ModelViewConst.Discount.LIST, discountService.search(dto, pageable));
        return ViewHtmlConst.Discounts.LIST;
    }

    @GetMapping("/add")
    public String showPageAdd(Model model) {
        return ViewHtmlConst.Discounts.CREATE;
    }

    @PostMapping("/add")
    public String submitAdd(Model model, @ModelAttribute("discountAddDto") DiscountRequestAddDto dto, RedirectAttributes ra) {
        ra.addFlashAttribute("messageSuccess", "Tạo thành công");
        discountService.create(dto);
        return redirect("/admin/discounts");
    }

    @GetMapping("/{id}/edit")
    public String showPageEdit(Model model, @PathVariable Integer id) {

        model.addAttribute("discountAddDto", discountService.findById(id));
        return ViewHtmlConst.Discounts.UPDATE;
    }

    @PostMapping("/{id}/edit")
    public String submitEditDiscount(Model model, @PathVariable Integer id, @ModelAttribute("discountAddDto") DiscountRequestAddDto dto, RedirectAttributes ra) {
        discountService.update(id, dto);
        ra.addFlashAttribute("messageSuccess", "Update thành công");
        return redirect("/admin/discounts/" + id + "/edit");
    }

}
