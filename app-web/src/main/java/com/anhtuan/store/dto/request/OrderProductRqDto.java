package com.anhtuan.store.dto.request;

import lombok.Data;

@Data
public class OrderProductRqDto {
    private Integer productId;
    private Integer quantity;
    private Integer sizeId;
}
