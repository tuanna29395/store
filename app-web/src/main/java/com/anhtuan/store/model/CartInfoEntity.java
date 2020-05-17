package com.anhtuan.store.model;

import com.anhtuan.store.dto.response.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartInfoEntity {

    private HashMap<Integer, CartItemDto> cartItems;

    private Integer amount;
}
