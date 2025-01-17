package com.mevy.stories.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.stories.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findAllByOrderByCreationDateDesc(Pageable pageable);

    @Transactional(readOnly = true)
    Optional<Book> findByName(String name);

    @Transactional(readOnly = true)
    boolean existsByName(String name);

}