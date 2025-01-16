package com.mevy.stories.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mevy.stories.entities.User;
import com.mevy.stories.repositories.UserRepository;
import com.mevy.stories.security.UserSpringSecurity;
import com.mevy.stories.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Passo 02: " + username);
        User user = this.userRepository.findByUsername(username).orElseThrow(
            () -> {
                System.out.println("Deu erro");
                return new UsernameNotFoundException("Usuário não encontrado");
            }
        );

        System.out.println("Passo 03: Chegou aqui " + user.getUsername() + " | " + user.getPassword() + " | " + user.getEmail());
        UserSpringSecurity userSpringSecurity = new UserSpringSecurity(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getAuthorities()
        );

        return userSpringSecurity;
    }
    
}
