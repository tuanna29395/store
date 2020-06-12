package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusType {
    NOT_DELETE(0),
    DELETED(1),
    ENABLE(2),
    DISABLED(3);
    private int val;
}
