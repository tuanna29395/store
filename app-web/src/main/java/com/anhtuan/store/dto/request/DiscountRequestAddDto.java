package com.anhtuan.store.dto.request;

import com.anhtuan.store.commons.constants.Commons;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DiscountRequestAddDto {
    private Integer id;
    private Integer percent;
    @DateTimeFormat(pattern = Commons.DATE_PATTERN)
    private Date startAt;
    @DateTimeFormat(pattern = Commons.DATE_PATTERN)
    private Date endAt;
    private String description;
}
