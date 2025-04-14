package com.mevy.metales_backend.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(

    @NotBlank(message = "The username field can't be null and must have at least 1 character.")
    @Size(
        max = 30,
        message = "The username field can't have more than 30 characters."
    )
    String username,

    @NotBlank(message = "The email field can't be null and must have at least 11 character.")
    @Size(
        min = 11,
        max = 50,
        message = "The email field must have between 11 and 50 characters."
    )
    String email,

    @NotBlank(message = "The password field can't be null and must have at least 8 character.")
    @Size(
        min = 8,
        message = "The password field must have at least 8 characters."
    )
    String password
) {
    
}
