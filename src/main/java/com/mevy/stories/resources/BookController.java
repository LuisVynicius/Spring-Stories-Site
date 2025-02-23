package com.mevy.stories.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.stories.dtos.GetCardBookDTO;
import com.mevy.stories.dtos.GetViewBookDTO;
import com.mevy.stories.services.BookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    
    private BookService bookService;

    @GetMapping("/recents")
    public ResponseEntity<List<GetCardBookDTO>> recentBooks() {
        List<GetCardBookDTO> cardBooksDTO = bookService.recentBooks();
        return ResponseEntity.ok().body(cardBooksDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GetViewBookDTO> getBook(
        @PathVariable String name
    ) {
        GetViewBookDTO viewBookDTO = bookService.getBook(name);
        return ResponseEntity.ok().body(viewBookDTO);
    }

}
