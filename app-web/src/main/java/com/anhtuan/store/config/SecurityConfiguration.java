package com.anhtuan.store.config;

import com.anhtuan.store.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthenticationServiceImpl authenticationServiceImpl;

    @Autowired
    private RefererRedirectionAuthenticationSuccessHandler refererRedirectionAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/webjars/**");
    }

    private static final String[] ANT_MATCHERS_RESOURCES = new String[]{
            "/bootstrap/**",
            "/css/**",
            "/fonts/**",
            "/images/**",
            "/js/**",
            "/lib/**",
            "/templates/**",
            "/vendor/**",
            "/login-process",
            "/ckeditor/**"

    };

    private static final String[] ANT_MATCHERS_ENDPOINT = new String[]{
            "/api/carts/delete",
            "/api/carts/update",
            "/users/*",
            "/api/categories/{id}/change-status",
            "/api/product/{id}/change-status",
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationServiceImpl).passwordEncoder(passwordEncoder());
    }

    private static final String[] ANT_MATCHERS_FREE_ENDPOINT = new String[]{
            "/",
            "/login*",
            "/carts/**",
            "/products/**",
            "/api/reset-password/**",
            "/users/*",
            "/api/**",
            "/api/carts/**",
            "/review/{productId}/reviews",
            "/password/**",
            "/reset-password/**"

    };



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .ignoringAntMatchers(ANT_MATCHERS_ENDPOINT)
                .and()
                .authorizeRequests()
                .antMatchers(ANT_MATCHERS_RESOURCES).permitAll()
                .antMatchers(ANT_MATCHERS_FREE_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email-login")
                .passwordParameter("password-login")
                .successHandler(refererRedirectionAuthenticationSuccessHandler)
                .failureUrl("/login-error")
                .defaultSuccessUrl("/products")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe()
                .key("cookieSecret").tokenValiditySeconds(1296000)
                .and()
                .exceptionHandling().accessDeniedPage("/login");

        http
                .requiresChannel()
                .anyRequest()
                .requiresSecure();

    }
}
