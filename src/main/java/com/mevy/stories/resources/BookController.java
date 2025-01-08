package com.mevy.stories.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.stories.entities.Book;
import com.mevy.stories.services.BookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    
    private BookService bookService;

    @GetMapping("/recents")
    public ResponseEntity<List<Book>> recentBooks() {
        List<Book> books = bookService.recentBooks();
        return ResponseEntity.ok().body(books);        
    }

}
