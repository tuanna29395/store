package com.anhtuan.store.dto.response;

import lombok.Data;

@Data
public class OrderReportDto {
    private Integer orderId;
    private Integer productId;
    private String productName;
    private String sizeName;
    private Integer quantity;
    private String salePrice;
    private String amount;
}
