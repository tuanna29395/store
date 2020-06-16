package com.anhtuan.store.controller.admin;

import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String showPageOrders(Model model) {
        model.addAttribute(ModelViewConst.Order.LIST, orderService.getAll());
        return ViewHtmlConst.AdminOrder.LIST;
    }
}
