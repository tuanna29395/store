package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.constants.ModelViewConst;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public abstract class BaseController {
    protected String redirect(String action) {
        return "redirect:" + action;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @ModelAttribute
    public void setCommonModel(Model model) {
        model.addAttribute(ModelViewConst.DISPLAY_DATETIME_FORMAT, Commons.DATETIME_PATTERN);
        model.addAttribute(ModelViewConst.DISPLAY_DATE_FORMAT, Commons.DATE_PATTERN);
        model.addAttribute(ModelViewConst.JS_DISPLAY_DATETIME_FORMAT, Commons.JS_DATETIME_PATTERN);
        model.addAttribute(ModelViewConst.JS_DISPLAY_DATE_FORMAT, Commons.JS_DATE_PATTERN);
    }

    protected String getBaseUrl(HttpServletRequest request) {
        int port = request.getServerPort();
        if (port != 80 && port != 443) {
            return request.getScheme() + "://" + request.getServerName() + ":" + port;
        }
        return request.getScheme() + "://" + request.getServerName();
    }
}
