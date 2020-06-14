package com.anhtuan.store.dto.response;

import com.anhtuan.store.commons.constants.Commons;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ProductResponseDto {
    private Integer id;

    private String name;

    private String imageUrl;

    private String salePrice;

    private String originalPrice;

    private String discountPrice;

    private String description;

    private CategoryResponseDto category;

    @DateTimeFormat(pattern = Commons.DATETIME_PATTERN)
    private Date updatedAt;

    private Integer status;
}
