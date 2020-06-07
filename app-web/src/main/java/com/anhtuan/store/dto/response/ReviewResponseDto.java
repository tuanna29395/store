package com.anhtuan.store.dto.response;

import com.anhtuan.store.commons.constants.Commons;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ReviewResponseDto {
    private Integer id;
    private UserDto user;
    private ProductResponseDto product;
    private String reviewContent;
    private Integer numberStar;
    @DateTimeFormat(pattern = Commons.DATETIME_PATTERN)
    private Date createdAt;
}
