package com.mevy.metales_backend.entities.dtos;

public record TaleUpdateAllDTO(

    Long id,
    String name,
    String description,
    String[] categories,
    ChapterViewDTO[] chapters

) {
    
}
