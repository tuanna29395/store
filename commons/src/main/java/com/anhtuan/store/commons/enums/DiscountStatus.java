package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountStatus {
    ACTIVE(1),
    IN_ACTIVE(2);
    private int val;
}
