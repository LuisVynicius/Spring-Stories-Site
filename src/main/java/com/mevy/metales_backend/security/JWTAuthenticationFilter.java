package com.mevy.metales_backend.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.exceptions.GlobalExceptionHandler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(
        AuthenticationManager authenticationManager,
        JWTUtil jwtUtil
    ) {
        setAuthenticationFailureHandler(new GlobalExceptionHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse
    ) {
        try {
            User userCredentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(
                userCredentials.getUsername(),
                userCredentials.getPassword()
            );

            Authentication authentication = this.authenticationManager.authenticate(authtoken);

            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        FilterChain filterChain,
        Authentication authentication
    ) {
        String email = ((UserSpringSecurity) authentication.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(email);
        httpServletResponse.addHeader("Authorization", "Bearer " + token);
        httpServletResponse.addHeader("access-control-expose-headers", "Authorization");
    }

}
