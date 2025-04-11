package com.mevy.metales_backend.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String email) {
        SecretKey secretKey = getSecretKeyBySecret();
        return Jwts.builder()
                    .subject(email)
                    .expiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(secretKey)
                    .compact();
    }

    private SecretKey getSecretKeyBySecret() {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        return secretKey;
    }

}
