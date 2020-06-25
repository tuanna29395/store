package com.anhtuan.store.dto.request;

import com.anhtuan.store.commons.constants.Commons;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SearchOrderDto {
    private Integer status;
    @DateTimeFormat(pattern = Commons.DATE_PATTERN)
    private Date fromDate;
    @DateTimeFormat(pattern = Commons.DATE_PATTERN)
    private Date endDate;
}
