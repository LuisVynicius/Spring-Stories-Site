package com.mevy.stories.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.stories.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Transactional(readOnly = true)
    Optional<User> findByUsername(String username);

    @Transactional(readOnly = true)
    boolean existsByEmail(String email);

}
