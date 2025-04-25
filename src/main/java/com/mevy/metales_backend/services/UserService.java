package com.mevy.metales_backend.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.entities.dtos.UserRegisterDTO;
import com.mevy.metales_backend.entities.dtos.UsernameDTO;
import com.mevy.metales_backend.exceptions.errors.DatabaseIntegrityException;
import com.mevy.metales_backend.exceptions.errors.ResourceNotFoundException;
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
        User user = this.registerDTOToUser(userRegisterDTO);

        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new DatabaseIntegrityException("Email já registrado.");
        }

        user = this.userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(
            () -> new ResourceNotFoundException("Usuário não encontrado")
        );
        return user;
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(
            () -> new ResourceNotFoundException("Usuário não encontrado")
        );
        return user;
    }

    @Transactional(readOnly = true)
    public User findUserByToken(String token) {
        String email = this.jwtUtil.getEmailByToken(token.substring(7));

        User user = this.findByEmail(email);

        return user;
    }

    public UsernameDTO usernameByToken(String token) {
        User user = this.findUserByToken(token);

        return new UsernameDTO(user.getUsername());
    }

    private User registerDTOToUser(UserRegisterDTO userRegisterDTO) {
        return User.builder()
                    .username(userRegisterDTO.username())
                    .email(userRegisterDTO.email())
                    .password(this.passwordEncoder.encode(userRegisterDTO.password()))
                    .build();
    }

}
