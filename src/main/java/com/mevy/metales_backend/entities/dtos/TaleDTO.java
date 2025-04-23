package com.mevy.metales_backend.entities.dtos;

public record TaleDTO(
    String title,
    String author,
    Integer chaptersQuantity,
    String updatedDate,
    Integer likeQuantity,
    String[] categories,
    String description
) {
    
}
