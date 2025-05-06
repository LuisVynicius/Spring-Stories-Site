package com.mevy.metales_backend.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.metales_backend.entities.Chapter;
import com.mevy.metales_backend.entities.Tale;
import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.entities.dtos.ChapterViewDTO;
import com.mevy.metales_backend.entities.dtos.TaleDTO;
import com.mevy.metales_backend.entities.dtos.TaleDeleteDTO;
import com.mevy.metales_backend.entities.dtos.TaleReadDTO;
import com.mevy.metales_backend.entities.dtos.TaleUpdateAllDTO;
import com.mevy.metales_backend.entities.dtos.TaleViewDTO;
import com.mevy.metales_backend.exceptions.errors.DatabaseIntegrityException;
import com.mevy.metales_backend.exceptions.errors.ResourceNotFoundException;
import com.mevy.metales_backend.exceptions.errors.ValidationException;
import com.mevy.metales_backend.repositories.TaleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaleService {
    
    private final TaleRepository taleRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<TaleDTO> findTales() {
        List<Tale> tales = this.taleRepository.findAll();

        List<TaleDTO> talesResult = tales.stream().map(x -> taleToTaleDTO(x)).toList();

        return talesResult;
    }

    @Transactional(readOnly = true)
    public List<TaleDTO> findTalesByUsername(String username) {
        User user = this.userService.findByUsername(username);
        List<Tale> tales = user.getTales().stream().toList();

        List<TaleDTO> talesResult = tales.stream()
                                        .map(x -> taleToTaleDTO(x))
                                        .toList();

        return talesResult;
    }

    @Transactional(readOnly = true)
    public TaleUpdateAllDTO findTaleToUpdate(String name) {
        Tale tale = findByName(name);
        
        TaleUpdateAllDTO taleUpsertDTO = this.taleToTaleUpsertDTO(tale);

        return taleUpsertDTO;
    }

    public Tale create(TaleUpdateAllDTO taleCreateDTO, String token) {
        Tale tale = this.taleCreateDtoToTale(taleCreateDTO);
        
        if (this.taleRepository.existsByName(tale.getName())) {
            throw new DatabaseIntegrityException("Uma história com esse nome já existe.");
        }

        User user = this.userService.findUserByToken(token);

        tale.setAuthor(user);

        this.taleRepository.save(tale);

        return tale;
    }

    public void update(TaleUpdateAllDTO taleUpdateAllDTO, String token) {
        Tale tale = this.findById(taleUpdateAllDTO.id());

        this.validAuthor(tale.getAuthor(), token);

        tale.setName(taleUpdateAllDTO.name());
        tale.setDescription(taleUpdateAllDTO.description());
        tale.setUpdationDate(Instant.now());
        //TODO Categories
        tale.setCategories(tale.getCategories());

        this.taleRepository.save(tale);
    }

    public void delete(TaleDeleteDTO taleDeleteDTO, String token) {
        Tale tale = this.findByName(taleDeleteDTO.name());
        
        this.validAuthor(tale.getAuthor(), token);

        this.taleRepository.delete(tale);

    }

    @Transactional(readOnly = true)
    public TaleViewDTO findTale(String name) {
        Tale tale = this.findByName(name);

        TaleViewDTO taleViewDTO = this.taleToTaleViewDTO(tale);

        return taleViewDTO;
    }

    @Transactional(readOnly = true)
    public TaleReadDTO findChapter(String name, Long number) {
        Tale tale = this.findByName(name);

        TaleReadDTO taleReadDTO = this.taleToTaleReadDTO(tale, number);

        return taleReadDTO;
    }

    @Transactional(readOnly = true)
    public List<TaleDTO> findMyTales(String token) {
        User user = this.userService.findUserByToken(token);

        return user.getTales()
                    .stream()
                    .map(tale -> taleToTaleDTO(tale))
                    .toList();
    }

    @Transactional(readOnly = true)
    public List<TaleDTO> findMyFavorites(String token) {
        User user = this.userService.findUserByToken(token);

        return user.getFavorites()
                    .stream()
                    .map(tale -> taleToTaleDTO(tale))
                    .toList();
    }

    private Tale findByName(String name) {
        Tale tale = this.taleRepository.findByName(name).orElseThrow(
            () -> new ResourceNotFoundException("História não encontrada")
        );

        return tale;
    }

    private Tale taleCreateDtoToTale(TaleUpdateAllDTO taleCreateDTO) {
        Tale tale = Tale.builder()
                        .name(taleCreateDTO.name())
                        .description(taleCreateDTO.description())
                        .categories(new HashSet<>())
                        .creationDate(Instant.now())
                        .updationDate(Instant.now())
                        .status(2)
                        .build();

        return tale;
    }

    private TaleDTO taleToTaleDTO(Tale tale) {

        TaleDTO taleDTO = new TaleDTO(
            tale.getName().length() > 26 ? tale.getName().substring(0, 23) + "..." : tale.getName(),
            tale.getAuthor().getUsername(),
            tale.getChapters().size(),
            this.formatDate(tale.getUpdationDate()),
            tale.getUsersLikes().size(),
            tale.getCategories().stream().map(
                category -> category.getName()
            ).toArray(String[]::new),
            tale.getDescription().length() > 300 ? tale.getDescription().substring(0, 297) + "..." : tale.getDescription()
        );

        return taleDTO;
    }

    private TaleUpdateAllDTO taleToTaleUpsertDTO(Tale tale) {
        ChapterViewDTO[] chapters = tale.getChapters()
                                        .stream()
                                        .map(chapter -> new ChapterViewDTO(chapter.getName(), this.formatDate(chapter.getCreationDate())))
                                        .toArray(ChapterViewDTO[]::new);

        TaleUpdateAllDTO taleUpdateAllDTO = new TaleUpdateAllDTO(
            tale.getId(),
            tale.getName(),
            tale.getDescription(),
            tale.getCategories()
                .stream()
                .map(category -> category.getName())
                .toArray(String[]::new),
            chapters
        );

        return taleUpdateAllDTO;
    }

    private TaleViewDTO taleToTaleViewDTO(Tale tale) {

        TaleViewDTO taleViewDTO = new TaleViewDTO(
            tale.getName(),
            tale.getAuthor().getUsername(),
            tale.getChapters().size(),
            tale.getUsersLikes().size(),
            tale.getUsersFavorites().size(),
            this.formatDate(tale.getCreationDate()),
            this.formatDate(tale.getUpdationDate()),
            tale.getStatus().getDescription(),
            tale.getCategories().stream().map(category -> category.getName()).toArray(String[]::new),
            tale.getDescription(),
            tale.getChapters()
                .stream()
                .sorted((x, y) -> x.getCreationDate().compareTo(y.getCreationDate()))
                .map(
                    chapter -> new ChapterViewDTO(chapter.getName(), this.formatDate(chapter.getCreationDate()))
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

    private String formatDate(Instant date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                                                .withZone(
                                                                    ZoneId.of("America/Sao_Paulo"
                                                                )
        );

        String formatedString = dateTimeFormatter.format(date);

        return formatedString;
    }

    private void validAuthor(User author, String token) {
        User user = this.userService.findUserByToken(token);

        if (author != user) {
            throw new ValidationException("História não pertence ao usuário desejado");
        }
    }

    @Transactional(readOnly = true)
    private Tale findById(Long id) {
        Tale tale = this.taleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("História não encontrado")
        );

        return tale;
    }

}