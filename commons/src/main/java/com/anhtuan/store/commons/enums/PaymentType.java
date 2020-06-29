package com.anhtuan.store.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {
    PAY_PAL("paypal",2),
    PAY_WHEN_RECEIVED("payreceived",1);
    private String value;
    private Integer type;
}
