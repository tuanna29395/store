package com.anhtuan.store.controller.api;

import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.OrderRqDto;
import com.anhtuan.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/order")
public class OrderApiController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void order(OrderRqDto dto, Principal user) {
//        orderService.orderProduct(dto, user);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<?> getDetailOrder(@PathVariable("id") Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderItemById(orderId));
    }
}
