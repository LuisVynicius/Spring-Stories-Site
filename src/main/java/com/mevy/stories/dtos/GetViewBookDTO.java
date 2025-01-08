package com.mevy.stories.dtos;

public record GetViewBookDTO(
    String name,
    String author,
    String creationDate,
    String updatedDate,
    String[] categories,
    String description,
    String[] chapters
) {
    
}
