package com.anhtuan.store.dto.request;

import com.anhtuan.store.commons.enums.StatusType;
import lombok.Data;

@Data
public class ProductSearchRqDto {
    private Integer categoryId;
    private Integer sortCondition;
    private String name;
    private Integer minPrice;
    private Integer maxPrice;

    private Integer status = StatusType.NOT_DELETE.getVal();
}
