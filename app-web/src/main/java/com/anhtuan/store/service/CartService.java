package com.anhtuan.store.service;

import com.anhtuan.store.dto.request.ToppingReq;
import com.anhtuan.store.dto.response.CartIdDto;
import com.anhtuan.store.dto.response.CartItemDto;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface CartService  {
    Map<CartIdDto, CartItemDto> addProductToCart(HttpSession session, Integer productId, ToppingReq toppingReq);

    List<CartItemDto> getAll(HttpSession session);

}
