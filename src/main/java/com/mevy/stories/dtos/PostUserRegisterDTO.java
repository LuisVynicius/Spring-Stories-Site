package com.mevy.stories.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUserRegisterDTO(

    @Size(
        min = 1,
        max = 50,
        message = "O Username deve ter entre 1 e 50 caracteres."
    )
    @NotBlank(
        message = "O Username não pode estar vazio."
    )
    String username,
    
    @Size(
        min = 10,
        max = 50,
        message = "O email deve ter entre 10 e 50 caracteres"
    )
    @NotBlank(
        message = "O email não pode estar vazio."
    )
    String email,

    @Size(
        min = 5,
        message = "A senha deve ter ao menos 5 caracteres."
    )
    @NotBlank(
        message = "A senha não pode estar vazia.."
    )
    String password
) {
    
}
