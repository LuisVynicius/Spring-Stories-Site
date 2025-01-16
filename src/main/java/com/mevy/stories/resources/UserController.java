package com.mevy.stories.resources;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.stories.dtos.PostUserRegisterDTO;
import com.mevy.stories.entities.User;
import com.mevy.stories.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
 
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.getAll();

        return ResponseEntity.ok().body(users);
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(
        @RequestBody @Valid PostUserRegisterDTO postUserRegisterDTO
    ) {
        userService.register(postUserRegisterDTO);
        return ResponseEntity.noContent().build();
    }

    
}
