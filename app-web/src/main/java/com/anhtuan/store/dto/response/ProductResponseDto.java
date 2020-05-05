package com.anhtuan.store.dto.response;

import lombok.Data;

@Data
public class ProductResponseDto {
    private String name;
    private String imageUrl;
    private String salePrice;
    private String discountPrice;
}
