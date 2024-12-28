package com.mevy.stories.services;

import org.springframework.stereotype.Service;

import com.mevy.stories.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;

}
