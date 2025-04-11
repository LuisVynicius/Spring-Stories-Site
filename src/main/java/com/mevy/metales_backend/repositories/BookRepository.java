package com.mevy.metales_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.metales_backend.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
