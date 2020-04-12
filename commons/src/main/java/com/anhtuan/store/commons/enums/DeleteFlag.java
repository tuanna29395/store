package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeleteFlag {
    DELETED(1),
    NOT_DELETE(0);
    private int val;
}
