package com.mevy.metales_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.metales_backend.entities.Tale;

@Repository
public interface TaleRepository extends JpaRepository<Tale, Long> {
    
    @Transactional(readOnly = true)
    Optional<Tale> findByName(String name);

    @Transactional(readOnly = true)
    boolean existsByName(String name);
    
    @Transactional(readOnly = true)
    boolean existsById(Long id);
}
