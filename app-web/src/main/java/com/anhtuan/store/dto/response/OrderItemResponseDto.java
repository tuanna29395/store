package com.anhtuan.store.dto.response;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private ProductResponseDto product;

    private Integer quantity;

    private String soldPrice;

    private SizeDto size;

    private String amount;

}
