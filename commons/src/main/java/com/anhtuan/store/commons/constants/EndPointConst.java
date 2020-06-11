package com.anhtuan.store.commons.constants;

public interface EndPointConst {
    String LOGIN = "/login";

    interface User {
        String USER_REGISTER = "/users/sign-up";
    }

    interface Products {
        String PRODUCT_LIST = "/products";
    }

    interface Checkouts {
        String CHECKOUT = "/checkout";
    }

    interface Password {
        String RESET_PASSWORD_URL = "/password/reset?token=";
    }

    interface Category{
        String ADMIN_LIST = "/admin/categories";
    }
}
