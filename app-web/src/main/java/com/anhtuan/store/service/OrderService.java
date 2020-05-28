package com.anhtuan.store.service;

import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.OrderRqDto;

public interface OrderService {
    void orderProduct(OrderRqDto dto, Principal user);
}
