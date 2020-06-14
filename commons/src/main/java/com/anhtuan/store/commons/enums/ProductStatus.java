package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    IN_STOCK(2),
    OUT_OF_STOCK(3);
    private int val;
}
