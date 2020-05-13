package com.anhtuan.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemId implements Serializable {
    private Integer orderId;

    private Integer productId;
}
