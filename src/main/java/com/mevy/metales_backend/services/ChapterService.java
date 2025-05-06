package com.mevy.metales_backend.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.mevy.metales_backend.entities.Chapter;
import com.mevy.metales_backend.entities.Tale;
import com.mevy.metales_backend.entities.dtos.ChapterCreate;
import com.mevy.metales_backend.entities.dtos.ChapterViewDTO;
import com.mevy.metales_backend.repositories.ChapterRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChapterService {
    
    private final ChapterRepository chapterRepository;
    private final TaleService taleService;
    
    public ChapterViewDTO create(ChapterCreate chapterCreate) {
        Tale tale = Tale.builder()
                        .id(chapterCreate.taleId())
                        .build();
        
        if (!this.taleService.existsById(tale.getId())) {
            // TODO Erro
            throw new RuntimeException("x");
        }

        Chapter chapter = Chapter.builder()
                                    .name(chapterCreate.name())
                                    .content(chapterCreate.content())
                                    .creationDate(Instant.now())
                                    .build();
            
        chapter.setTale(tale);

        chapter = this.chapterRepository.save(chapter);

        ChapterViewDTO chapterViewDTO = this.chapterToChapterViewDTO(chapter);

        return chapterViewDTO;
    }

    private ChapterViewDTO chapterToChapterViewDTO(Chapter chapter) {
        ChapterViewDTO chapterViewDTO = new ChapterViewDTO(
            chapter.getName(),
            this.formatDate(chapter.getCreationDate())
        );

        return chapterViewDTO;
    }

    private String formatDate(Instant date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                                                .withZone(
                                                                    ZoneId.of("America/Sao_Paulo"
                                                                )
        );

        String formatedString = dateTimeFormatter.format(date);

        return formatedString;
    }

}
