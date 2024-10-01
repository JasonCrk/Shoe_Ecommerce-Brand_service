package com.shoe_ecommerce.brand.context.presentation.middlewares.filters;

import com.shoe_ecommerce.brand.context.domain.ports.services.jwt.JwtService;
import com.shoe_ecommerce.brand.context.shared.domain.AuthUser;

import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

import java.io.IOException;

public final class JwtAuthFilter implements Filter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null) return;

        if (!authHeader.startsWith("Bearer ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or invalid");
            return;
        }

        String accessToken = authHeader.substring(7);

        try {
            AuthUser user = jwtService.getUserFromToken(accessToken);
            httpRequest.setAttribute("user", user);

            filterChain.doFilter(httpRequest, httpResponse);
        } catch (Exception e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
        }
    }
}
