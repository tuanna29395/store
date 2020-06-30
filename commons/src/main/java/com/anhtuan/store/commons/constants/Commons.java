package com.anhtuan.store.commons.constants;

public interface Commons {
    String EMAIL_PATTERN = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
    String ID = "{id}";
    String SESSION_URL_PRIOR_LOGIN = "url_prior_login";
    String SLASH = "/";
    String DATETIME_PATTERN = "MM/dd/yyyy HH:mm";
    String JS_DATETIME_PATTERN = "MM/DD/YYYY HH:mm";
    String DATE_PATTERN = "MM/dd/yyyy";
    String JS_DATE_PATTERN = "MM/DD/YYYY";
    String SUBJECT_MAIL_FORGOT_PASSWORD = "Mail request reset password";
    String LOGIN_FACEBOOK_URL = "https://www.facebook.com/dialog/oauth?client_id=304352020704159&redirect_uri=https://localhost:8082/login-facebook";
}
