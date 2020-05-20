package com.anhtuan.store.dto.request;

import com.anhtuan.store.commons.enums.SizeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class ToppingReq {
    private Integer sizeId = SizeType.DEFAULT.getVal();
    private Integer quantity = 1;
}
