package com.anhtuan.store.dto.request;

import com.anhtuan.store.commons.enums.StatusType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
public class ProductAddEditDto {
    @NotEmpty(message = "Name is invalid")
    private String name;

    private Integer categoryId;

    @NotEmpty(message = "Origin price is invalid")
    private Integer originPrice;

    @NotEmpty(message = "Sale price is invalid")
    private Integer salePrice;

    private Integer discountId;

    private String description;

    private MultipartFile fileImage;

    private Integer status = StatusType.ENABLE.getVal();
}
