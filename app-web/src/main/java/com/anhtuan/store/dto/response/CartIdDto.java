package com.anhtuan.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded  = true)
public class CartIdDto {

    @EqualsAndHashCode.Include
    private Integer productId;

    @EqualsAndHashCode.Include
    private Integer sizeId;
}
