package com.mevy.stories.dtos;

public record GetCardBookDTO(
    String name,
    String author,
    Integer quantity,
    String updatedDate,
    String[] categories,
    String description
) {
    
}
