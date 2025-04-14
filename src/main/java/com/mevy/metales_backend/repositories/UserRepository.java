package com.mevy.metales_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.metales_backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username);

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email);

}
