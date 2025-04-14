package com.mevy.metales_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class Seed implements CommandLineRunner {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        User user = User.builder()
                        .username("Username")
                        .email("Email")
                        .password(passwordEncoder.encode("Password"))
                        .build();
        
        userRepository.save(user);

    }
    
}
