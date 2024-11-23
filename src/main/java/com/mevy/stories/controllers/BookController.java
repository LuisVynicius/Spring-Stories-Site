package com.mevy.stories.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mevy.stories.dtos.BookDTO;
import com.mevy.stories.entities.Book;
import com.mevy.stories.services.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookService.fromDTO(bookDTO);

        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/id")
                                            .buildAndExpand(book.getId())
                                            .toUri();

        return ResponseEntity.created(uri).build();
    }

}
