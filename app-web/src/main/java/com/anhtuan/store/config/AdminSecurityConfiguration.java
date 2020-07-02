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

@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthenticationServiceImpl authenticationServiceImpl;

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

    private static final String[] ANT_MATCHERS_SECURE_ENDPOINT = new String[]{
            "/admin/**",
    };

    private static final String[] ANT_MATCHERS_RESOURCES = new String[]{
            "/admin/login",
            "/admin/login-error",
            "/admin/vendor/**",
            "/admin/css/**",
            "/admin/js/**",
            "/bootstrap/**",
            "/css/**",
            "/fonts/**",
            "/images/**",
            "/js/**",
            "/lib/**",
            "/templates/**",
            "/vendor/**",
            "/login-process",
            "/ckeditor/**",
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().and().
                antMatcher("/admin/**").authorizeRequests()
                .antMatchers(ANT_MATCHERS_RESOURCES).permitAll()
                    .antMatchers(ANT_MATCHERS_SECURE_ENDPOINT).hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/admin/admin-login")
                .loginPage("/admin/login")
                .usernameParameter("email-login")
                .passwordParameter("password-login")
                .defaultSuccessUrl("/admin")
                .failureUrl("/admin/login-error")
                .and()
                .logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().accessDeniedPage("/admin/login-error");
    }


}
