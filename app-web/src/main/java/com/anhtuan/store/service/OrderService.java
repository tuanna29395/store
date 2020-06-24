package com.anhtuan.store.service;

import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.OrderRqDto;
import com.anhtuan.store.dto.request.SearchOrderDto;
import com.anhtuan.store.dto.response.OrderItemResponseDto;
import com.anhtuan.store.dto.response.OrderResponseDto;
import org.springframework.data.jpa.repository.Query;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {
    void orderProduct(OrderRqDto dto, Principal user, HttpSession session);

    List<OrderResponseDto> getAll(SearchOrderDto dto);

    List<OrderItemResponseDto> getOrderItemById(Integer OrderId);
}
