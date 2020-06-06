package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.exception.ErrorObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
@Controller
public class CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = 404;
            ErrorObject errorObject = new ErrorObject();
            // display specific error page
            errorObject.setCode(statusCode);
            errorObject.setMessage(ErrorMessage.NOT_FOUND);
            model.addAttribute(errorObject);
        }
        return ViewHtmlConst.ERROR;
    }
}
