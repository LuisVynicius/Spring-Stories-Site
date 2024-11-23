package com.mevy.stories.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mevy.stories.dtos.BookDTO;
import com.mevy.stories.entities.Book;
import com.mevy.stories.entities.Category;
import com.mevy.stories.repositories.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    
    private BookRepository bookRepository;

    public Book create(Book bookDTO) {
        Book book = bookDTO;

        book = bookRepository.save(book);

        return book;
    }

    public Book fromDTO(BookDTO bookDTO) {
        return Book
                    .builder()
                    .title(bookDTO.title())
                    .description(bookDTO.description())
                    .categories(
                        bookDTO
                                .categories()
                                .stream()
                                .map(x -> Category
                                    .builder()
                                    .name(x.name())
                                    .build()
                                )
                                .collect(Collectors.toSet())
                                
                    )
                    .build();
    }

}
