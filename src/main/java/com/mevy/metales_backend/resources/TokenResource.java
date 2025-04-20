package com.mevy.metales_backend.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.metales_backend.entities.dtos.TokenValidDTO;
import com.mevy.metales_backend.security.JWTUtil;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class TokenResource {
    
    private final JWTUtil jwtUtil;

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validToken(@RequestBody @Valid TokenValidDTO tokenValidDTO) {
        Boolean isValid = jwtUtil.isValidToken(tokenValidDTO.token().substring(7));
        
        if (isValid) {
            return ResponseEntity.ok(isValid);
        }

        return ResponseEntity.badRequest().body(isValid);

    }

}
