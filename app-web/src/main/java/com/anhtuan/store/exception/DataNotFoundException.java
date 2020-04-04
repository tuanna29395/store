package com.anhtuan.store.exception;


public class DataNotFoundException extends Exception {

    public DataNotFoundException() {
        this(new ErrorObject());
    }

    public DataNotFoundException(ErrorObject errorObject) {
        super(errorObject);
    }
}
