package com.anhtuan.store.commons.constants;

public interface ViewHtmlConst {
    String ERROR = "error";

    interface Users {
        String SIGN_UP_SIGN_IN = "login";
    }

    interface Products {
        String LIST = "/products/list";
        String DETAIL = "products/detail";
        String ADMIN_LIST = "/admin-product/list";
        String ADMIN_CREATE = "/admin-product/create";
        String ADMIN_UPDATE = "/admin-product/update";

    }

    interface Carts {
        String LIST = "/cart/list";
        String REDIRECT_CARTS = "redirect:/carts";
    }

    interface Checkout {
        String CHECKOUT = "/checkout/checkout";
    }

    interface Password {
        String FORGOT_PASSWORD = "/password/forgot";
        String REDIRECT_FORGOT_PASSWORD = "redirect:/password/forgot";
    }

    interface AdminCategory {
        String List = "/admin-category/list";
        String CREATE = "/admin-category/create";
        String EDIT = "/admin-category/update";
    }

    interface AdminOrder {
        String LIST = "/admin-order/order";
    }
}
