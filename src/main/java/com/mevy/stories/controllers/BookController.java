package com.mevy.stories.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mevy.stories.entities.Book;
import com.mevy.stories.services.BookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Void> create() {
        Book book = bookService.create(
            Book.builder()
                        .title("Book01")
                        .build()
        );

        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/id")
                                            .buildAndExpand(book.getId())
                                            .toUri();

        return ResponseEntity.created(uri).build();
    }

}
