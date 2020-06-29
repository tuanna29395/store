package com.anhtuan.store.dto.request;

import com.anhtuan.store.commons.enums.PaymentType;
import lombok.Data;

@Data
public class OrderRqDto {
    private String fullName;
    private String address;
    private String phoneNumber;
    private Integer typePayment = PaymentType.PAY_WHEN_RECEIVED.getType();
}
