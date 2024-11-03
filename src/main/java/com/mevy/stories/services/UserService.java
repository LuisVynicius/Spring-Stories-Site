package com.mevy.stories.services;

import org.springframework.stereotype.Service;

import com.mevy.stories.entities.User;
import com.mevy.stories.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;

    public User create(User userDTO) {
        User user = userDTO;
        
        user = userRepository.save(user);

        return user;
    }

}
