package com.anhtuan.store.exception;


public class DataConflictException extends Exception {

    public DataConflictException() {
        this(new ErrorObject());
    }

    public DataConflictException(ErrorObject errorObject) {
        super(errorObject);
    }
}
