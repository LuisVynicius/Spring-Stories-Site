package com.mevy.metales_backend.services;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.metales_backend.entities.Tale;
import com.mevy.metales_backend.entities.dtos.ChapterDTO;
import com.mevy.metales_backend.entities.dtos.TaleDTO;
import com.mevy.metales_backend.entities.dtos.TaleViewDTO;
import com.mevy.metales_backend.repositories.TaleRepository;
import com.mevy.metales_backend.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaleService {
    
    private final TaleRepository taleRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TaleDTO> findTales() {
        List<Tale> tales = taleRepository.findAll();

        List<TaleDTO> talesResult = tales.stream().map(x -> taleToTaleDTO(x)).toList();

        return talesResult;
    }

    @Transactional(readOnly = true)
    public TaleViewDTO findTale(String name) {
        // TODO Erro
        Tale tale = taleRepository.findByName(name).get();

        TaleViewDTO taleViewDTO = taleToTaleViewDTO(tale);

        return taleViewDTO;
    }

    private TaleDTO taleToTaleDTO(Tale tale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                                                .withZone(
                                                                    ZoneId.of("America/Sao_Paulo")
                                                                );

        return new TaleDTO(
            tale.getName(),
            tale.getAuthor().getUsername(),
            tale.getChapters().size(),
            dateTimeFormatter.format(tale.getCreationDate()),
            dateTimeFormatter.format(tale.getUpdationDate()),
            tale.getStatus().getDescription(),
            tale.getUsersLikes().size(),
            tale.getCategories().stream().map(
                category -> category.getName()
            ).toArray(String[]::new),
            tale.getDescription()
        );
    }

    private TaleViewDTO taleToTaleViewDTO(Tale tale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                                                .withZone(
                                                                    ZoneId.of("America/Sao_Paulo")
                                                                );

        TaleViewDTO taleViewDTO = new TaleViewDTO(
            tale.getName(),
            tale.getAuthor().getUsername(),
            tale.getChapters().size(),
            tale.getUsersLikes().size(),
            tale.getUsersFavorites().size(),
            dateTimeFormatter.format(tale.getCreationDate()),
            dateTimeFormatter.format(tale.getUpdationDate()),
            tale.getStatus().getDescription(),
            tale.getCategories().stream().map(category -> category.getName()).toArray(String[]::new),
            tale.getDescription(),
            tale.getChapters().stream().map(
                chapter -> new ChapterDTO(chapter.getName(), dateTimeFormatter.format(chapter.getCreationDate()))
            ).toArray(ChapterDTO[]::new)
        );

        return taleViewDTO;
    }

}