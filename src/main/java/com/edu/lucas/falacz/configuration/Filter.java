package com.edu.lucas.falacz.configuration;

import com.edu.lucas.falacz.middleware.Auth;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Filter {

    @Bean
    public FilterRegistrationBean<Auth> filterJwt(Properties props) {
        FilterRegistrationBean<Auth> filterRb = new FilterRegistrationBean<>();
        filterRb.setFilter(new Auth(props));
        filterRb.addUrlPatterns("/post");
        return filterRb;

    }
}
