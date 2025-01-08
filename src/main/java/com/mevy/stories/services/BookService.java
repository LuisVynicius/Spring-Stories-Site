package com.mevy.stories.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.stories.dtos.GetCardBookDTO;
import com.mevy.stories.dtos.GetViewBookDTO;
import com.mevy.stories.entities.Book;
import com.mevy.stories.entities.Category;
import com.mevy.stories.entities.Chapter;
import com.mevy.stories.repositories.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<GetCardBookDTO> recentBooks() {
        List<Book> books = bookRepository.findAllByOrderByCreationDateDesc(Pageable.ofSize(40));
        List<GetCardBookDTO> cardBooks = toCardDTO(books);
        return cardBooks;
    }

    @Transactional(readOnly = true)
    public GetViewBookDTO getBook(String name) {
        Book book = bookRepository.findByName(name).get();
        
        GetViewBookDTO getViewBookDTO = toViewDTO(book);

        return getViewBookDTO;
    }

    public GetCardBookDTO toCardDTO(Book book) {
        GetCardBookDTO getCardBookDTO = new GetCardBookDTO(
            book.getName(),
            book.getAuthor().getUsername(),
            book.getChapters().size(),
            book.getUpdatedDate().toString(),
            getCategories(book),
            book.getDescription()
        );

        return getCardBookDTO;
    }

    public List<GetCardBookDTO> toCardDTO(List<Book> books) {
        List<GetCardBookDTO> getCardBookDTOs = books.stream()
                                                    .map(x -> toCardDTO(x))
                                                    .toList();

        return getCardBookDTOs;
    }

    public GetViewBookDTO toViewDTO(Book book) {
        GetViewBookDTO getViewBookDTO = new GetViewBookDTO(
            book.getName(),
            book.getAuthor().getUsername(),
            book.getCreationDate().toString(),
            book.getUpdatedDate().toString(),
            getCategories(book),
            book.getDescription(),
            getChapters(book)
        );

        return getViewBookDTO;
    }

    public List<GetViewBookDTO> toViewDTO(List<Book> books) {
        List<GetViewBookDTO> getViewBookDTOs = books.stream()
                                                    .map(x -> toViewDTO(x))
                                                    .toList();
        
        return getViewBookDTOs;
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

    private String[] getChapters(Book book) {
        int size = book.getChapters().size();
        String[] chapters = new String[size];

        int i = 0;
        for (Chapter chapter : book.getChapters()) {
            chapters[i] = chapter.getName();
            i++;
        }

        return chapters;
    }

}
