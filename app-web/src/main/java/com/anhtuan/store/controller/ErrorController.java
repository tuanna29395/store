package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.constants.Messages;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.exception.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            ErrorObject errorObject = new ErrorObject();
            // display specific error page
            errorObject.setCode((Integer) status);
            errorObject.setMessage(ErrorMessage.HAS_ERROR);
            model.addAttribute("error",errorObject);
        }
        return ViewHtmlConst.ERROR;
    }
}
