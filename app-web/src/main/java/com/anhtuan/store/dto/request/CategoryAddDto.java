package com.anhtuan.store.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryAddDto {
    @NotEmpty(message = "Name category invalid")
    private String name;

    private Integer id;
}
