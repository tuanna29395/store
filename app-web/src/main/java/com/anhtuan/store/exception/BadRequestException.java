package com.anhtuan.store.exception;

public class BadRequestException extends Exception {
    public BadRequestException() {
        this(new ErrorObject());
    }

    public BadRequestException(ErrorObject errorObject) {
        super(errorObject);
    }
}
