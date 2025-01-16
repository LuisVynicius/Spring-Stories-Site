package com.mevy.stories.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        List<User> users = this.userRepository.findAll();
        
        return users;
    }

    public void register(PostUserRegisterDTO postUserRegisterDTO) {
        if (!userRepository.existsByEmail(postUserRegisterDTO.email())) {
            User user = User.builder()
                        .username(postUserRegisterDTO.username())
                        .email(postUserRegisterDTO.email())
                        .password(passwordEncoder.encode(postUserRegisterDTO.password()))
                        .build();
            this.userRepository.save(user);
        } else {
            throw new ResourceSaveException(User.class, "Email já utilizado.");
        }
    }

}
