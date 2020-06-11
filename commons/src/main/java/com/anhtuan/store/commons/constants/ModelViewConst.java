package com.anhtuan.store.commons.constants;

public interface ModelViewConst {
    String DISPLAY_DATETIME_FORMAT = "displayDatetimeFormat";
    String DISPLAY_DATE_FORMAT = "displayDateFormat";
    String JS_DISPLAY_DATETIME_FORMAT = "jsDisplayDatetimeFormat";
    String JS_DISPLAY_DATE_FORMAT = "jsDisplayDateFormat";

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

    interface  Reviews {
        String REVIEW_INFO = "reviewInfo";
    }

    interface Password {
        String PASSWORD_FORGOT_DTO ="passwordForgotDto";
    }

    interface Category {
        String CATEGORY_PAGEABLE ="categories";
        String CATEGORY_SEARCH = "categorySearchDto";
    }
}
