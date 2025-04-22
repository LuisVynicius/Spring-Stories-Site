package com.mevy.metales_backend.entities.dtos;

public record TaleViewDTO(
    String name,
    String author,
    Integer chaptersQuantity,
    Integer likeQuantity,
    Integer favoritesQuantity,
    String creationDate,
    String updatedDate,
    String status,
    String[] categories,
    String description,
    ChapterDTO[] chapters
) {
    
}
