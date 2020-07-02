package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeLoginFlag {
    FACEBOOK_LOGIN(1),
    INTERNAL_LOGIN(0);
    private int val;
}
