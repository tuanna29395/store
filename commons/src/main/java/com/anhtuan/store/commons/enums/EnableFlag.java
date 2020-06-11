package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnableFlag {
    ENABLE(1),
    DISABLE(0);

    private int val;
}
