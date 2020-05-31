package com.anhtuan.store.commons.constants;

public interface ModelViewConst {
    interface User {
        String USER_REGISTER_DTO = "userRegisterDto";
    }

    interface Product {
        String PRODUCT_PAGEABLE = "products";
        String SEARCH_DTO = "searchDto";
        String PRODUCT_DETAIL = "productDetail";
    }

    interface Carts {
        String TOPPING = "toppingRq";
        String LIST = "items";
    }

    interface Sizes {
        String LIST = "sizes";
    }

    interface checkouts {
        String USER_INFO = "userInfo";
        String CART_INFO = "cartInfo";
        String TOTAL_CART = "totalCart";
        String ORDER_INFO = "orderInfo";

    }
}
