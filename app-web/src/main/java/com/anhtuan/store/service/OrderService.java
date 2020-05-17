package com.anhtuan.store.service;

import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.OrderProductRqDto;

public interface OrderService {
    void orderProduct(OrderProductRqDto dto, Principal user);
}
