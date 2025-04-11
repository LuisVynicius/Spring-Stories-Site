package com.mevy.metales_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.metales_backend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
