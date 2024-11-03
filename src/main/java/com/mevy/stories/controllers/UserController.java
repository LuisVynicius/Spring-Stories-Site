package com.mevy.stories.controllers;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mevy.stories.entities.User;
import com.mevy.stories.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> create() {
        User user = userService.create(
            User.builder()
                .username("user")
                .email("email@gmail.com")
                .password("password")
                .createAt(LocalDateTime.now())
                .build()
        );

        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/id")
                                            .buildAndExpand(user.getId())
                                            .toUri();
        
        return ResponseEntity.created(uri).build();
    }

}
