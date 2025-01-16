package com.mevy.stories.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mevy.stories.entities.User;
import com.mevy.stories.resources.exceptions.GlobalExceptionHandler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        setAuthenticationFailureHandler(new GlobalExceptionHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException{
        try {
            User userCredentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userCredentials.getUsername(),
                userCredentials.getPassword(),
                new ArrayList<>()
            );
            Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return authentication;
        } catch (IOException e) {
            throw new AuthenticationServiceException("Username ou senha inválido");
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        FilterChain filterChain,
        Authentication authentication
    ) throws IOException, ServletException{
        UserSpringSecurity userSpringSecurity = (UserSpringSecurity) authentication.getPrincipal();
        String username = (userSpringSecurity).getUsername();
        String token = jwtUtil.generateToken(username);
        httpServletResponse.addHeader("Authorization", "Bearer " + token);
        httpServletResponse.addHeader("access-control-expose-headers", "Authorization");
    }

}
