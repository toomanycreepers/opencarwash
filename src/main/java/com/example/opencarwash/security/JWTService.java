package com.example.opencarwash.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JWTService {
    private static final Logger logger = LoggerFactory
            .getLogger(JWTService.class);

    @Value("${spring.security.jwt.expirationAccessMs}")
    private long jwtExpirationAccessMs;

    @Value("${spring.security.jwt.expirationRefreshMs}")
    private long jwtExpirationRefreshMs;
    @Value("${spring.security.jwt.secret}")
    private String jwtSecret;

    public String generateJwtAccessToken(UserDetailsImpl authentication) {
        return Jwts.builder()
                .setSubject((authentication.getUsername()))
                .claim("roles", authentication.getAuthorities().stream()
                        .map(role -> role.getAuthority())
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationAccessMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateJwtRefreshToken(UserDetailsImpl authentication) {
        return Jwts.builder()
                .setSubject((authentication.getUsername()))
                .claim("roles", authentication.getAuthorities().stream()
                        .map(role -> role.getAuthority())
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationRefreshMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isValid(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
