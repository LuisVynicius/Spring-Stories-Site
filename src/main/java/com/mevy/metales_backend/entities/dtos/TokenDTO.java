package com.mevy.metales_backend.entities.dtos;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(

    @NotBlank(message = "The token field can't be null and must have at least 1 character.")
    String token

) {
    
}
