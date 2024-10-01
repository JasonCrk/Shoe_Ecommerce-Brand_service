package com.shoe_ecommerce.brand.context.presentation.middlewares;

import com.shoe_ecommerce.brand.context.domain.ports.services.jwt.JwtService;
import com.shoe_ecommerce.brand.context.presentation.middlewares.filters.JwtAuthFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public final class MiddlewareConfiguration {

    private final JwtService jwtService;

    public MiddlewareConfiguration(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> authFilter() {
        FilterRegistrationBean<JwtAuthFilter> middlewareRegistration = new FilterRegistrationBean<>();
        middlewareRegistration.setFilter(new JwtAuthFilter(jwtService));
        middlewareRegistration.addUrlPatterns("/api/v1/brands/**");
        return middlewareRegistration;
    }
}
