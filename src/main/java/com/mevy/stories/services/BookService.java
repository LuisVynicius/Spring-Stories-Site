package com.mevy.stories.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mevy.stories.dtos.GetCardBookDTO;
import com.mevy.stories.entities.Book;
import com.mevy.stories.entities.Category;
import com.mevy.stories.repositories.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    
    private BookRepository bookRepository;

    public List<Book> recentBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public GetCardBookDTO toDTO(Book book) {
        GetCardBookDTO getCardBookDTO = new GetCardBookDTO(
            book.getName(),
            book.getAuthor().getUsername(),
            book.getChapters().size(),
            book.getUpdatedDate().toString().substring(0, 10),
            getCategories(book),
            book.getDescription()
        );

        return getCardBookDTO;
    }

    public List<GetCardBookDTO> toDTO(List<Book> book) {
        List<GetCardBookDTO> getCardBookDTOs = book.stream()
                                                    .map(x -> toDTO(x))
                                                    .toList();

        return getCardBookDTOs;
    }

    private String[] getCategories(Book book) {
        int size = book.getCategories().size();
        String[] categories = new String[size];

        int i = 0;
        for (Category category : book.getCategories()) {
            categories[i] = category.getName();
            i++;
        }

        return categories;
    }

}
