package com.mevy.stories.configs;


import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mevy.stories.entities.Book;
import com.mevy.stories.entities.Category;
import com.mevy.stories.entities.Chapter;
import com.mevy.stories.entities.User;
import com.mevy.stories.repositories.BookRepository;
import com.mevy.stories.repositories.CategoryRepository;
import com.mevy.stories.repositories.ChapterRepositorie;
import com.mevy.stories.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@Profile("dev")
@AllArgsConstructor
public class Seed implements CommandLineRunner {

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private ChapterRepositorie chapterRepositorie;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user01 = User.builder()
                        .username("User01")
                        .email("User01@gmail.com")
                        .password(passwordEncoder.encode("password01"))
                        .build();

        User user02 = User.builder()
                        .username("User02")
                        .email("User02@gmail.com")
                        .password(passwordEncoder.encode("password02"))
                        .build();

        userRepository.saveAll(
            Arrays.asList(
                user01,
                user02
            )
        );

        Category category01 = Category.builder()
                                        .name("Category01")
                                        .build();

        Category category02 = Category.builder()
                                        .name("Category02")
                                        .build();

        categoryRepository.saveAll(
            Arrays.asList(
                category01,
                category02
            )
        );

        Book book01 = Book.builder()
                            .name("Minecraft Book")
                            .creationDate(Instant.now())
                            .updatedDate(Instant.now())
                            .description("Its a Minecraft book")
                            .author(user01)
                            .categories(
                                Set.of(category01)
                            )
                            .build();
        
        Book book02 = Book.builder()
                            .name("PVZ Book")
                            .creationDate(Instant.now())
                            .updatedDate(Instant.now())
                            .description("Its a PVZ book")
                            .author(user02)
                            .categories(
                                Set.of(category02)
                            )
                            .build();

        bookRepository.saveAll(
            Arrays.asList(
                book01,
                book02
            )
        );

        Chapter chapter01Book01 = Chapter.builder()
                                            .name("Passo 01")
                                            .text("Text01")
                                            .book(book01)
                                            .creationDate(Instant.now())
                                            .build();
                        
        chapterRepositorie.save(chapter01Book01);

        user01.setFavorites(new HashSet<>());

        user01.getFavorites().add(book01);

        userRepository.save(user01);
    }
    
}
