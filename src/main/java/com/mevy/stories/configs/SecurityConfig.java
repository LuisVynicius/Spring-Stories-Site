package com.mevy.stories.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import com.mevy.stories.security.JwtAuthenticationFilter;
import com.mevy.stories.security.JwtAuthorizationFilter;
import com.mevy.stories.security.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private static final String[] PUBLIC_MATCHERS_POST = {
        "/login",
        "/user/register"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
        "/book/recents",
        "/book/name/**",
        "/user"
    };

    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                                    .passwordEncoder(passwordEncoder());
        this.authenticationManager = authenticationManagerBuilder.build();

        return http
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .cors(cors -> cors.configurationSource(request -> {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.addAllowedHeader("*");
                        corsConfiguration.addAllowedMethod("*");
                        corsConfiguration.addAllowedOrigin("*");
                        return corsConfiguration;
                    }))
                    .authenticationManager(authenticationManager)
                    .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtUtil))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, jwtUtil, userDetailsService))
                    .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                        .anyRequest().authenticated()
                    )
                    .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
