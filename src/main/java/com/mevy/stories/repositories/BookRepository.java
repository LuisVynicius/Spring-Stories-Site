package com.mevy.stories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.stories.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
