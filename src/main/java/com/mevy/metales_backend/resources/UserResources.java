package com.mevy.metales_backend.resources;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.entities.dtos.UserRegisterDTO;
import com.mevy.metales_backend.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserResources {
    
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
        @RequestBody
        @Valid
        UserRegisterDTO userRegisterDTO
    ) {
        User user = userService.register(userRegisterDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                            .path("/{id}")
                                            .buildAndExpand(user.getId())
                                            .toUri();
        
        return ResponseEntity.created(uri).build();
    }

}
