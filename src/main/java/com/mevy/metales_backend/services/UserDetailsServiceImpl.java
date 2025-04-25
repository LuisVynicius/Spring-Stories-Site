package com.mevy.metales_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.repositories.UserRepository;
import com.mevy.metales_backend.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email = username;

        User user = this.userRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException("Email não cadastrado")
        );

        return new UserSpringSecurity(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            user.getProfiles()
        );
    }

}
