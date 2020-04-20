package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    IN_STOCK(1),
    OUT_OF_STOCK(0);
    private int val;
}
