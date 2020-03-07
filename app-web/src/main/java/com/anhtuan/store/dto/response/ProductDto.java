package com.anhtuan.store.dto.response;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String image;
    private Double salePrice;
    private Double discountPrice;
}
