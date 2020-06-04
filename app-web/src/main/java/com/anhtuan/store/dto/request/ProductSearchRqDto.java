package com.anhtuan.store.dto.request;

import lombok.Data;

@Data
public class ProductSearchRqDto {
    private Integer categoryId;
    private Integer sortCondition;
    private String name;
    private Integer minPrice;
    private Integer maxPrice;
}
