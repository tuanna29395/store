package com.anhtuan.store.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Exception extends RuntimeException {

    private ErrorObject errorObject;

    public Exception() {
        this(new ErrorObject());
    }

    public Exception(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

    public Exception(String message) {
        super(message);
    }

    public static Exception.Builder serviceError() {
        return new Exception.Builder(Exception.class);
    }

    public static Exception.Builder dataNotFound() {
        return new Exception.Builder(DataNotFoundException.class);
    }

    public static Exception.Builder dataConflict() {
        return new Exception.Builder(DataConflictException.class);
    }

    public static Exception.Builder badRequest() {
        return new Exception.Builder(BadRequestException.class);
    }

    public static class Builder {
        private Class<? extends Exception> exceptionClass;

        public Builder(Class<? extends Exception> exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public Exception build(String errorMessage) {
            ErrorObject errorObject = new ErrorObject();
            errorObject.setMessage(errorMessage);
            return createCorrespondingException(errorObject);
        }

        private Exception createCorrespondingException(ErrorObject errorObject) {
            if (exceptionClass == DataNotFoundException.class) {
                return new DataNotFoundException(errorObject);
            }

            if (exceptionClass == Exception.class) {
                return new Exception(errorObject);
            }

            if (exceptionClass == DataConflictException.class) {
                return new DataConflictException(errorObject);
            }

            if (exceptionClass == BadRequestException.class) {
                return new BadRequestException(errorObject);
            }

            throw new UnsupportedOperationException(exceptionClass.getSimpleName() + " is not supported");
        }
    }
}
