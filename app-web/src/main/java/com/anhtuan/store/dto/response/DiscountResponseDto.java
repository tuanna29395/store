package com.anhtuan.store.dto.response;

import lombok.Data;

@Data
public class DiscountResponseDto {
    private Integer id;
    private Double percent;
    private String description;
}
