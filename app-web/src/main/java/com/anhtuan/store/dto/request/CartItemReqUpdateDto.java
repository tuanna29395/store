package com.anhtuan.store.dto.request;

import lombok.Data;

@Data
public class CartItemReqUpdateDto {
    private Integer productId;
    private Integer sizeIdOld;
    private Integer sizeIdNew;
    private Integer quantity;
}
