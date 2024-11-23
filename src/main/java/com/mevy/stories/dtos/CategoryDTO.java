package com.mevy.stories.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDTO (
    
    @NotBlank
    @Size(min = 1, max = 15)
    String name
) {
    
}
