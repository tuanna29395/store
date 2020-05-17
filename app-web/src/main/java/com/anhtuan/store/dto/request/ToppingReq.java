package com.anhtuan.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class ToppingReq {
    private Integer sizeId;
    private Integer quantity;
}
