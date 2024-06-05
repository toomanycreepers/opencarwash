package com.example.opencarwash.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTResponse {
    public String accessToken;
    public String refreshToken;
}
