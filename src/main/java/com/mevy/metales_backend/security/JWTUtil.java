package com.mevy.metales_backend.security;

import java.util.Date;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

    public Boolean isValidToken(String token) {
        Claims claims = getClaims(token);

        if (Objects.nonNull(claims)) {
            String email = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date currentDate = new Date(System.currentTimeMillis());

            if (Objects.nonNull(email) && Objects.nonNull(expirationDate) && currentDate.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    public String getEmailByToken(String token) {
        Claims claims = getClaims(token);
        
        if (Objects.nonNull(claims)) {
            String email = claims.getSubject();
            return email;
        }
        
        return null;
    }

    private SecretKey getSecretKeyBySecret() {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        return secretKey;
    }

    private Claims getClaims(String token) {
        SecretKey secretKey = getSecretKeyBySecret();

        try {
            return Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
        } catch (Exception e) {
            return null;
        }
    }

}
