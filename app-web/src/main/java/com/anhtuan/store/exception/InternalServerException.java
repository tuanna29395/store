package com.anhtuan.store.exception;


public class InternalServerException extends Exception {

    public InternalServerException() {
        this(new ErrorObject());
    }

    public InternalServerException(ErrorObject errorObject) {
        super(errorObject);
    }
}
