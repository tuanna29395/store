package com.anhtuan.store.dto.response;

import lombok.Data;

@Data
public class ReportRevenueDto {
    private Integer orderId;
    private Integer productId;
    private String productName;
    private String soldPrice;
    private String sizeName;
    private String sizePrice;
    private Double percentDiscount;
    private String amount;
    private Integer quantity;
}
