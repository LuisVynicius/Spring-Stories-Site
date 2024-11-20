package com.mevy.stories.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mevy.stories.entities.Book;
import com.mevy.stories.entities.Category;
import com.mevy.stories.entities.Chapter;
import com.mevy.stories.entities.User;
import com.mevy.stories.repositories.BookRepository;
import com.mevy.stories.repositories.CategoryRepository;
import com.mevy.stories.repositories.ChapterRepository;
import com.mevy.stories.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@Profile("dev")
@AllArgsConstructor
public class Config implements CommandLineRunner {

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private ChapterRepository chapterRepository;

    @Override
    public void run(String... args) throws Exception {
        Category category01 = Category
                                .builder()
                                .name("action")
                                .build();
                    
        Category category02 = Category
                                .builder()
                                .name("adventure")
                                .build();

        categoryRepository.saveAll(Arrays.asList(
            category01, category02
        ));

        User user = User
                        .builder()
                        .username("user01")
                        .password("senha1234")
                        .email("email@gmail.com")
                        .createAt(LocalDateTime.now())
                        .build();
        
        userRepository.save(user);

        Book book = Book
                        .builder()
                        .title("Book01")
                        .user(user)
                        .description("Descrição do livro 1")
                        .build();

        bookRepository.save(book);

        Chapter chapter01 = Chapter
                                .builder()
                                .title("Chapter01")
                                .lines(
                                    new String[] {
                                        "Linha numero 01",
                                        "Linha numero 02",
                                        "Linha numero 03"
                                    }
                                )
                                .book(book)
                                .build();
                            
        Chapter chapter02 = Chapter
                                .builder()
                                .title("Chapter02")
                                .lines(
                                    new String[] {
                                        "Linha numero 01",
                                        "Linha numero 02",
                                        "Linha numero 03"
                                    }
                                )
                                .book(book)
                                .build();

        chapterRepository.saveAll(
            Arrays.asList(
                chapter01, chapter02
            )
        );
    }
    
}
