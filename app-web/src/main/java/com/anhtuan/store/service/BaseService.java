package com.anhtuan.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class BaseService {
    @Autowired
    private MessageSource messageSource;

    protected String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    protected String getMessage(String key, Object[] objects) {
        return messageSource.getMessage(key, objects, LocaleContextHolder.getLocale());
    }
}
