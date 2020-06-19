package com.anhtuan.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private ProductResponseDto product;
    private Integer quantity;
    private SizeDto size;
    private String amount;

    public String calculateAmount() {
        int priceSize = size.getId() == null ? 0 : size.getPrice();
        String priceProduct = product.getSalePrice();

        if (product.getIsDiscount()) {
            priceProduct = product.getDiscountPrice();
        }

        return String.format("%,d", (convertPrice(priceProduct) + priceSize) * quantity);
    }

    private Integer convertPrice(String price) {
        return Integer.parseInt(price.replace(",", ""));
    }
}
