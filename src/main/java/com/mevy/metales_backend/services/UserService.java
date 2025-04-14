package com.mevy.metales_backend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.entities.dtos.UserRegisterDTO;
import com.mevy.metales_backend.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterDTO userRegisterDTO) {
        User user = registerDTOToUser(userRegisterDTO);

        user = userRepository.save(user);

        return user;
    }

    private User registerDTOToUser(UserRegisterDTO userRegisterDTO) {
        return User.builder()
                    .username(userRegisterDTO.username())
                    .email(userRegisterDTO.email())
                    .password(passwordEncoder.encode(userRegisterDTO.password()))
                    .build();
    }

}
