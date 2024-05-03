package com.example.opencarwash.security;

import io.jsonwebtoken.impl.DefaultJwtParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import io.jsonwebtoken.*;
import java.security.Key;

public class RefreshTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JWTService jwtService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String refresh_token = request.getCookies()[0].getValue();
        if (jwtService.isValid(refresh_token)) {
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}