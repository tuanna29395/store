package com.anhtuan.store.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class SearchOrderDto {
    private Integer status;
    private Date fromDate;
    private Date endDate;
}
