package com.mevy.metales_backend.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaleUpsertDTO(

    @NotBlank(message = "The name field can't be null and must have at least 1 character.")
    @Size(
        max = 80,
        message = "The name field can't have more than 80 characters."
    )
    String name,
    
    @NotBlank(message = "The description field can't be null and must have at least 1 character.")
    @Size(
        max = 500,
        message = "The description field can't have more than 500 characters."
    )
    String description,

    String[] categories

) {
    
}
