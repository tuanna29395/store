package com.anhtuan.store.dto.response;

import lombok.Data;

@Data
public class OrderResponseDto {
    private Integer id;

    private UserDto user;

    private String payment;

    private String orderName;

    private String orderPhone;

    private String orderAddress;

    private String status;

    private Long totalPrice;

}
