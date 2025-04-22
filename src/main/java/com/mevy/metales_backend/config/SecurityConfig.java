package com.mevy.metales_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import com.mevy.metales_backend.security.JWTAuthenticationFilter;
import com.mevy.metales_backend.security.JWTUtil;

@Configuration
public class SecurityConfig {
    
    private final String[] PUBLIC_MATCHES_POST = new String[] {
        "/login",
        "/user/register",
        "/token/validate"
    };

    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                                    .passwordEncoder(passwordEncoder());
        this.authenticationManager = authenticationManagerBuilder.build();

        return http
                    .csrf(csrf -> csrf.disable())
                    .cors(cors -> cors
                        .configurationSource(config -> {
                            CorsConfiguration corsConfiguration = new CorsConfiguration();
                            corsConfiguration.addAllowedOrigin("*");
                            corsConfiguration.addAllowedMethod("*");
                            corsConfiguration.addAllowedHeader("*");
                            return corsConfiguration;
                        })
                    )
                    .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, PUBLIC_MATCHES_POST).permitAll()
                        .requestMatchers(HttpMethod.GET, "/tales/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tales/**").permitAll()
                        .anyRequest().authenticated()
                    )
                    .authenticationManager(authenticationManager)
                    .addFilter(new JWTAuthenticationFilter(authenticationManager, jwtUtil))
                    .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
