package com.mevy.metales_backend.config;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mevy.metales_backend.entities.Category;
import com.mevy.metales_backend.entities.Chapter;
import com.mevy.metales_backend.entities.Tale;
import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.entities.enums.TaleStatus;
import com.mevy.metales_backend.repositories.CategoryRepository;
import com.mevy.metales_backend.repositories.ChapterRepository;
import com.mevy.metales_backend.repositories.TaleRepository;
import com.mevy.metales_backend.repositories.UserRepository;

import io.jsonwebtoken.lang.Arrays;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class Seed implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaleRepository taleRepository;
    private final CategoryRepository categoryRepository;
    private final ChapterRepository chapterRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user1 = User.builder()
                .username("eldor")
                .email("eldor@example.com")
                .password(passwordEncoder.encode("123456"))
                .build();

        User user2 = User.builder()
                .username("cryon")
                .email("cryon@example.com")
                .password(passwordEncoder.encode("654321"))
                .build();

        User user3 = User.builder()
                .username("nova")
                .email("nova@example.com")
                .password(passwordEncoder.encode("abcdef"))
                .build();

        userRepository.saveAll(
            Set.of(user1, user2, user3)
        );

        Category catAction = Category.builder().name("Ação").build();
        Category catFantasy = Category.builder().name("Fantasia").build();
        Category catAdventure = Category.builder().name("Aventura").build();
        Category catDrama = Category.builder().name("Drama").build();
        Category catScifi = Category.builder().name("Sci-fi").build();
        Category catMystery = Category.builder().name("Mistério").build();
        Category catRomance = Category.builder().name("Romance").build();

        categoryRepository.saveAll(
            Set.of(
                catAction, catFantasy, catAdventure,
                catDrama, catScifi, catMystery, catRomance
            )
        );

        Tale tale1 = Tale.builder()
                .name("Ecos de Eldoria")
                .description("Heróis esquecidos lutam para restaurar um mundo partido.")
                .creationDate(Instant.now())
                .UpdationDate(Instant.now())
                .status(TaleStatus.ONGOING.getCode())
                .author(user1)
                .categories(Set.of(catFantasy, catAdventure, catAction))
                .build();

        Tale tale2 = Tale.builder()
                .name("Gelo Eterno")
                .description("A guerra entre clãs congelados e criaturas ancestrais.")
                .creationDate(Instant.now())
                .UpdationDate(Instant.now())
                .status(TaleStatus.HIATUS.getCode())
                .author(user2)
                .categories(Set.of(catFantasy, catDrama))
                .build();

        Tale tale3 = Tale.builder()
                .name("Luzes de Neon")
                .description("Num futuro cyberpunk, a verdade está escondida em memórias roubadas.")
                .creationDate(Instant.now())
                .UpdationDate(Instant.now())
                .status(TaleStatus.FINALIZED.getCode())
                .author(user3)
                .categories(Set.of(catScifi, catMystery))
                .build();

        Tale tale4 = Tale.builder()
                .name("Entre Estrelas e Promessas")
                .description("Dois corações conectados em diferentes planetas.")
                .creationDate(Instant.now())
                .UpdationDate(Instant.now())
                .status(TaleStatus.ONGOING.getCode())
                .author(user3)
                .categories(Set.of(catRomance, catScifi))
                .build();

        taleRepository.saveAll(
            Set.of(tale1, tale2, tale3, tale4)
        );

        Chapter cap1 = Chapter.builder()
                      .name("Prólogo - Ecos Despertam")
                      .content("Capítulo introdutório...")
                      .creationDate(Instant.now())
                      .build();

        Chapter cap2 = Chapter.builder()
                            .name("Capítulo 1 - O Chamado")
                            .content("A aventura começa...")
                            .creationDate(Instant.now())
                            .build();

        Chapter cap3 = Chapter.builder()
                            .name("Capítulo 2 - Nevasca de Sangue")
                            .content("Batalha no norte gelado...")
                            .creationDate(Instant.now())
                            .build();

        Chapter cap4 = Chapter.builder()
                            .name("Capítulo 1 - Memórias Fragmentadas")
                            .content("Exploração da mente digital...")
                            .creationDate(Instant.now())
                            .build();

        Chapter cap5 = Chapter.builder()
                            .name("Capítulo 1 - O Sinal das Estrelas")
                            .content("Mensagem do além...")
                            .creationDate(Instant.now())
                            .build();

        cap1.setTale(tale1);
        cap2.setTale(tale1);
        cap3.setTale(tale2);
        cap4.setTale(tale3);
        cap5.setTale(tale4);

        chapterRepository.saveAll(
            Set.of(
                cap1,
                cap2,
                cap3,
                cap4,
                cap5
            )
        );

        user1.setLikes(new HashSet<>());

        user1.getLikes().add(tale2);

        userRepository.save(user1);

        user1.setFavorites(new HashSet<>());
        user2.setFavorites(new HashSet<>());
        user3.setFavorites(new HashSet<>());

        user1.getFavorites().add(tale2);
        user2.getFavorites().add(tale2);
        user3.getFavorites().add(tale2);

        userRepository.saveAll(
            Set.of(
                user1,
                user2,
                user3
            )
        );

    }
}
