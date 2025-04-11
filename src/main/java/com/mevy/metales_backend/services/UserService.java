package com.mevy.metales_backend.services;

import org.springframework.stereotype.Service;

import com.mevy.metales_backend.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

}
