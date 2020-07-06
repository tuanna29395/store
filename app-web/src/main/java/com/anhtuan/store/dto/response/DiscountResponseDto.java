package com.anhtuan.store.dto.response;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.utils.DateTimeUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DiscountResponseDto {
    private Integer id;

    private Integer percent;

    private String description;

    @DateTimeFormat(pattern = Commons.DATETIME_PATTERN)
    private Date startAt;

    @DateTimeFormat(pattern = Commons.DATETIME_PATTERN)
    private Date endAt;

    private boolean status;

    public boolean isActive() {
        Date startAt = DateTimeUtils.addTime(this.startAt, 0, 0, 0);
        Date end = DateTimeUtils.addTime(this.endAt, 23, 59, 59);
        Date now = new Date();
        return startAt.before(now) && now.before(end);
    }
}
