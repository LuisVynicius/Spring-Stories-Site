package com.mevy.metales_backend.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChapterCreate(

    @NotNull(message = "The taleId field can't be null")
    Long taleId,

    @NotBlank(message = "The name field can't be null and must have at least 1 character.")
    @Size(
        max = 50,
        message = "The name field can't have more than 50 characters."
    )
    String name,
    
    @NotBlank(message = "The content field can't be null and must have at least 1 character.")
    @Size(
        max = 2000,
        message = "The content field can't have more than 2000 characters."
    )
    String content

) {
    
}
