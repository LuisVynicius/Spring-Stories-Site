package com.mevy.metales_backend.services.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mevy.metales_backend.entities.dtos.LanguageToolResponseDTO;

@FeignClient(name = "languageToolClient", url = "https://api.languagetool.org")
public interface LanguageToolClient {
    
    @PostMapping(value = "/v2/check", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    LanguageToolResponseDTO checkGrammar(
        @RequestHeader("Content-Type") String contentType,
        @RequestBody MultiValueMap<String, String> body
    );

}