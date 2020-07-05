package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortProductType {
    PRICE_ASC(1),
    PRICE_DECS(2),
    DISCOUNTING(3);


    private Integer val;
}
