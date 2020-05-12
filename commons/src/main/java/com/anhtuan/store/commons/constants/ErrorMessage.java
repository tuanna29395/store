package com.anhtuan.store.commons.constants;

public interface ErrorMessage {
    String DUPLICATED_DATA = "%s is already exists";
    interface Role{
        String ROLE_NOT_FOUND = "Role ID {id} not found.";
    }

    interface User{
        String USER_NOT_FOUND = "Login ID {id} not found.";
    }

    interface Product{
        String PRODUCT_NOT_FOUND = "Product Id %s not found";
    }

}
