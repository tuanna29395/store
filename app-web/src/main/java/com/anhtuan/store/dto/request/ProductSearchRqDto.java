package com.anhtuan.store.dto.request;

import lombok.Data;

@Data
public class ProductSearchRqDto {
    private Integer categoryId;
    private Integer sortCondition;
}
