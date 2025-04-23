package com.mevy.metales_backend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.entities.dtos.UserRegisterDTO;
import com.mevy.metales_backend.entities.dtos.UsernameDTO;
import com.mevy.metales_backend.repositories.UserRepository;
import com.mevy.metales_backend.security.JWTUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public User register(UserRegisterDTO userRegisterDTO) {
        User user = registerDTOToUser(userRegisterDTO);

        user = userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        // TODO erro
        User user = userRepository.findByUsername(username).get();
        return user;
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        // TODO erro
        User user = userRepository.findByEmail(email).get();
        return user;
    }

    @Transactional(readOnly = true)
    public User findUserByToken(String token) {
        String email = jwtUtil.getEmailByToken(token.substring(7));

        User user = findByEmail(email);

        return user;
    }

    public UsernameDTO usernameByToken(String token) {
        User user = findUserByToken(token);

        return new UsernameDTO(user.getUsername());
    }

    private User registerDTOToUser(UserRegisterDTO userRegisterDTO) {
        return User.builder()
                    .username(userRegisterDTO.username())
                    .email(userRegisterDTO.email())
                    .password(passwordEncoder.encode(userRegisterDTO.password()))
                    .build();
    }

}
