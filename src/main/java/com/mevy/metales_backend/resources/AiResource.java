package com.mevy.metales_backend.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.metales_backend.entities.dtos.AiTextPostDTO;
import com.mevy.metales_backend.services.LanguageToolService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ai")
@AllArgsConstructor
public class AiResource {
    
    private final LanguageToolService languageToolService;

    @PostMapping("/check")
    public ResponseEntity<String> checkText(@RequestBody AiTextPostDTO aiTextPostDTO) {
        String text = languageToolService.checkText(aiTextPostDTO.text());

        return ResponseEntity.ok().body(text);
    }

}
