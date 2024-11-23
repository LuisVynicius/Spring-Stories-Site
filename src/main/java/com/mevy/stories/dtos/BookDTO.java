package com.mevy.stories.dtos;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookDTO(

    @NotBlank
    @Size(min = 1, max = 30)
    String title,

    @NotBlank
    @Size(min = 1, max = 255)
    String description,

    @NotNull
    Set<CategoryDTO> categories

) {
    
}
