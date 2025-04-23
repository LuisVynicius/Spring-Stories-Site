package com.mevy.metales_backend.services;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.metales_backend.entities.Chapter;
import com.mevy.metales_backend.entities.Tale;
import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.entities.dtos.ChapterViewDTO;
import com.mevy.metales_backend.entities.dtos.TaleDTO;
import com.mevy.metales_backend.entities.dtos.TaleReadDTO;
import com.mevy.metales_backend.entities.dtos.TaleViewDTO;
import com.mevy.metales_backend.repositories.TaleRepository;
import com.mevy.metales_backend.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaleService {
    
    private final TaleRepository taleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

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

    @Transactional(readOnly = true)
    public TaleReadDTO findChapter(String name, Long number) {
        // TODO Erro
        Tale tale = taleRepository.findByName(name).get();

        TaleReadDTO taleReadDTO = taleToTaleReadDTO(tale, number);

        return taleReadDTO;
    }

    @Transactional(readOnly = true)
    public List<TaleDTO> findMyTales(String token) {
        User user = userService.findUserByToken(token);

        return user.getTales()
                    .stream()
                    .map(tale -> taleToTaleDTO(tale))
                    .toList();
    }

    @Transactional(readOnly = true)
    public List<TaleDTO> findMyFavorites(String token) {
        User user = userService.findUserByToken(token);

        return user.getFavorites()
                    .stream()
                    .map(tale -> taleToTaleDTO(tale))
                    .toList();
    }

    private TaleDTO taleToTaleDTO(Tale tale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                                                .withZone(
                                                                    ZoneId.of("America/Sao_Paulo"
                                                                )
        );

        return new TaleDTO(
            tale.getName().length() > 26 ? tale.getName().substring(0, 23) + "..." : tale.getName(),
            tale.getAuthor().getUsername(),
            tale.getChapters().size(),
            dateTimeFormatter.format(tale.getUpdationDate()),
            tale.getUsersLikes().size(),
            tale.getCategories().stream().map(
                category -> category.getName()
            ).toArray(String[]::new),
            tale.getDescription().length() > 300 ? tale.getDescription().substring(0, 297) + "..." : tale.getDescription()
        );
    }

    private TaleViewDTO taleToTaleViewDTO(Tale tale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                                                .withZone(
                                                                    ZoneId.of("America/Sao_Paulo"
                                                                )
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
            tale.getChapters()
                .stream()
                .sorted((x, y) -> x.getCreationDate().compareTo(y.getCreationDate()))
                .map(
                    chapter -> new ChapterViewDTO(chapter.getName(), dateTimeFormatter.format(chapter.getCreationDate()))
                )
                .toArray(ChapterViewDTO[]::new)
        );

        return taleViewDTO;
    }

    private TaleReadDTO taleToTaleReadDTO(Tale tale, Long number) {

        Chapter chapter = tale.getChapters()
                                .stream()
                                .sorted((x, y) -> x.getCreationDate().compareTo(y.getCreationDate()))
                                .toList()
                                .get(number.intValue() - 1);

        TaleReadDTO taleReadDTO = new TaleReadDTO(
            tale.getName(),
            tale.getAuthor().getUsername(),
            chapter.getName(),
            chapter.getContent()
        );

        return taleReadDTO;
    }

}