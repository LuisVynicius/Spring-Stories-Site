package com.mevy.stories.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCategoryCreateDTO(

    @Size(
        max = 25,
        message = "O nome da categoria deve ter no máximo 25 caracteres"
    )
    @NotBlank(
        message = "O nome não pode estar vazio"
    )
    String name

) {
    
}
