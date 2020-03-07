package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    NOT_DELETE(0), DELETED(1);
    private final int value;
}
