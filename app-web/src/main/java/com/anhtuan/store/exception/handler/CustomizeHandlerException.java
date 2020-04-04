package com.anhtuan.store.exception.handler;

import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.exception.BadRequestException;
import com.anhtuan.store.exception.DataNotFoundException;
import com.anhtuan.store.exception.ErrorObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class CustomizeHandlerException {

    @ExceptionHandler(Exception.class)
    public final String handleException(Exception ex, Model model) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof DataNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof DataNotFoundException) {
            status = HttpStatus.CONFLICT;
        } else if (ex instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
        }

        ErrorObject errorObject = new ErrorObject();
        errorObject.setCode(status.value());
        errorObject.setMessage(ex.getMessage());
        model.addAttribute(errorObject);

        return getErrorPath();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleACLMethodException(AccessDeniedException ex, Model model) {
        log.error(ex.getMessage(), ex);
        ErrorObject errorObject = new ErrorObject(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());
        model.addAttribute(errorObject);

        return getErrorPath();
    }

    private String getErrorPath() {
        return ViewHtmlConst.ERROR;
    }
}
