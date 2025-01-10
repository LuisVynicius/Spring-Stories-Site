package com.mevy.stories.configs;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private static final String[] PUBLIC_MATCHERS_POST = {
        "/user/login",
        "/user/register"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
        "/book/recents",
        "/book/name/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                    .csrf(csrf -> csrf.disable())
                    .cors(cors -> cors.configurationSource(request -> {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.addAllowedHeader("*");
                        corsConfiguration.addAllowedMethod("*");
                        corsConfiguration.addAllowedOrigin("*");
                        return corsConfiguration;
                    }))
                    .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                        .anyRequest().authenticated()
                    )
                    .build();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
