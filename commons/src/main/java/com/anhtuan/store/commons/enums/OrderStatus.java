package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PROCESSING(0),
    CANCELLED(1),
    COMPLETED(2);
    private int value;
}
