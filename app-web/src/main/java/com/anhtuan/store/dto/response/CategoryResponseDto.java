package com.anhtuan.store.dto.response;

import com.anhtuan.store.commons.constants.Commons;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CategoryResponseDto {
    private Integer id;
    private String name;
    private Integer productCount;
    @DateTimeFormat(pattern = Commons.DATETIME_PATTERN)
    private Date updatedAt;
}
