package com.anhtuan.store.dto.response;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private ProductResponseDto product;

    private Integer quantity;

    private String soldPrice;

    private Integer percentDiscount;

    private SizeDto size;

    private Integer sizePrice;
    private String amount;

}
