package com.mevy.stories.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mevy.stories.dtos.PostUserRegisterDTO;
import com.mevy.stories.entities.User;
import com.mevy.stories.repositories.UserRepository;
import com.mevy.stories.services.exceptions.ResourceSaveException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(PostUserRegisterDTO postUserRegisterDTO) {
        User user = User.builder()
                        .username(postUserRegisterDTO.username())
                        .email(postUserRegisterDTO.email())
                        .password(bCryptPasswordEncoder.encode(postUserRegisterDTO.password()))
                        .build();

        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        } else {
            throw new ResourceSaveException(User.class, "Email já utilizado.");
        }
    }

}
