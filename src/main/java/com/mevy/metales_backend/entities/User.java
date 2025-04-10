package com.mevy.metales_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "tb_user",
    indexes = @Index(columnList = "email")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        updatable = false,
        unique = true
    )
    private Long id;

    @Column(
        nullable = false
    )
    private String username;
    
    @Column(
        nullable = false,
        updatable = false,
        unique = true,
        length = 50
    )
    private String email;

    @Column(
        nullable = false
    )
    private String password;
}
