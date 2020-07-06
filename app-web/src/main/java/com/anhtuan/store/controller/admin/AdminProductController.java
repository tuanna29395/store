package com.anhtuan.store.controller.admin;

import com.anhtuan.store.commons.constants.EndPointConst;
import com.anhtuan.store.commons.constants.Messages;
import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.controller.BaseController;
import com.anhtuan.store.dto.request.CategorySearchDto;
import com.anhtuan.store.dto.request.ProductAddEditDto;
import com.anhtuan.store.dto.request.ProductSearchRqDto;
import com.anhtuan.store.service.CategoryService;
import com.anhtuan.store.service.CommonService;
import com.anhtuan.store.service.DiscountService;
import com.anhtuan.store.service.ProductService;
import com.anhtuan.store.support.MessageHelper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DiscountService discountService;

    @Autowired
    private CommonService commonService;

    @GetMapping
    public String products(Model model, @ModelAttribute(ModelViewConst.Product.SEARCH_DTO) ProductSearchRqDto searchRqDto) {
        model.addAttribute(ModelViewConst.Product.PRODUCT_PAGEABLE, productService.getAll(searchRqDto));
        model.addAttribute(ModelViewConst.Product.SEARCH_DTO, new ProductSearchRqDto());
        return ViewHtmlConst.Products.ADMIN_LIST;
    }

    @GetMapping("/add")
    public String showCreateProductPage(Model model) {

        model.addAttribute(ModelViewConst.Product.PRODUCT_ADD_EDIT_DTO, new ProductAddEditDto());
        model.addAttribute(ModelViewConst.Product.CATEGORY_LIST, categoryService.getAll(new CategorySearchDto()));
        model.addAttribute(ModelViewConst.Product.DISCOUNT_LIST, discountService.getAll());

        return ViewHtmlConst.Products.ADMIN_CREATE;
    }

    @SneakyThrows
    @PostMapping("/add")
    public String submitCreate(Model model,
                               @Valid @ModelAttribute(ModelViewConst.Product.PRODUCT_ADD_EDIT_DTO) ProductAddEditDto productAddEditDto, BindingResult bindingResult,RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelViewConst.Product.PRODUCT_ADD_EDIT_DTO, productAddEditDto);
            model.addAttribute(ModelViewConst.Product.CATEGORY_LIST, categoryService.getAll(new CategorySearchDto()));
            model.addAttribute(ModelViewConst.Product.DISCOUNT_LIST, discountService.getAll());
            return ViewHtmlConst.Products.ADMIN_CREATE;
        }
        productService.createProduct(productAddEditDto);
        MessageHelper.addSuccessAttribute(ra, String.format(Messages.REGISTER_SUCCESS, Messages.PRODUCT));
        return redirect(EndPointConst.Products.ADMIN_LIST);
    }

    @GetMapping("/{id}/edit")
    public String showUpdateProductPage(Model model, @PathVariable Integer id) {
        model.addAttribute(ModelViewConst.Product.PRODUCT_ADD_EDIT_DTO, productService.getProductAdminDetail(id));
        model.addAttribute(ModelViewConst.Product.CATEGORY_LIST, categoryService.getAll(new CategorySearchDto()));
        model.addAttribute(ModelViewConst.Product.DISCOUNT_LIST, discountService.getAll());
        return ViewHtmlConst.Products.ADMIN_UPDATE;
    }

    @PostMapping("/{id}/edit")
    public String submitEdit(Model model,
                             @Valid @ModelAttribute(ModelViewConst.Product.PRODUCT_ADD_EDIT_DTO) ProductAddEditDto productAddEditDto,
                             BindingResult bindingResult,
                             @PathVariable Integer id,
                             RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelViewConst.Product.PRODUCT_ADD_EDIT_DTO, productService.getProductAdminDetail(id));
            model.addAttribute(ModelViewConst.Product.CATEGORY_LIST, categoryService.getAll(new CategorySearchDto()));
            model.addAttribute(ModelViewConst.Product.DISCOUNT_LIST, discountService.getAll());
            return ViewHtmlConst.Products.ADMIN_UPDATE;
        }

        productService.update(productAddEditDto, id);
        MessageHelper.addSuccessAttribute(ra, String.format(Messages.UPDATE_SUCCESS, Messages.PRODUCT));
        return redirect(String.format(EndPointConst.Products.ADMIN_EDIT, id));
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return redirect(EndPointConst.Products.ADMIN_LIST);
    }
}
