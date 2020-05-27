package com.anhtuan.store.config;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.constants.EndPointConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("authenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (defaultSavedRequest != null) {
            String targetURL = defaultSavedRequest.getRequestURI();
            if (targetURL.equals(Commons.SLASH)) {
                redirectStrategy.sendRedirect(request, response, EndPointConst.Products.PRODUCT_LIST);
            } else {
                redirectStrategy.sendRedirect(request, response, targetURL);
            }
        } else {
            redirectStrategy.sendRedirect(request, response, EndPointConst.Products.PRODUCT_LIST);
        }
    }
}
