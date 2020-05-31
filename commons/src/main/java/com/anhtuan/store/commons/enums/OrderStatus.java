package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PROCESSING(1),
    CANCELLED(2),
    COMPLETED(3);
    private int value;
}
