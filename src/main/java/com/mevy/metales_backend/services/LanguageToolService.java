package com.mevy.metales_backend.services;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.mevy.metales_backend.entities.dtos.LanguageToolResponseDTO;
import com.mevy.metales_backend.services.interfaces.LanguageToolClient;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LanguageToolService {

    private final LanguageToolClient languageToolClient;

    public String checkText(String texto) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("text", texto);
        map.add("language", "pt-BR");

        LanguageToolResponseDTO languageToolResponseDTO = languageToolClient.checkGrammar("application/x-www-form-urlencoded", map);

        StringBuilder response = new StringBuilder();

        languageToolResponseDTO.getMatches().forEach(match -> {
            response.append("* Erro: ")
                    .append(match.getMessage())
                    .append("\n");
        
            if (match.getReplacements() != null && !match.getReplacements().isEmpty()) {
                response.append("* Sugestões:\n");
                match.getReplacements().forEach(replacement ->
                    response.append("- ").append(replacement.getValue()).append("\n")
                );
            } else {
                response.append("* Sem sugestões disponíveis.\n");
            }
        
            response.append("\n");
        });

        return response.toString();
    }
}

