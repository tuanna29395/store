package com.anhtuan.store.dto.request;

import com.anhtuan.store.commons.enums.StatusType;
import lombok.Data;

@Data
public class CategorySearchDto {
    private Integer id;
    private String name;
    private Integer status = StatusType.NOT_DELETE.getVal();
}
