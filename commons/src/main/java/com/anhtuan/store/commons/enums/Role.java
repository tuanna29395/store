package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN(1),
    CUSTOMER(2);
    private int val;
}
