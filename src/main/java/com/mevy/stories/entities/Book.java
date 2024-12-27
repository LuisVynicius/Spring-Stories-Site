package com.mevy.stories.entities;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        nullable = false,
        unique = true,
        length = 50
    )
    private String name;

    @Column()
    private String description;

    @Column(
        nullable = false
    )
    @JsonFormat(
        pattern = "yyyy-MM-dd'T'HH:mm::ss'Z'",
        timezone = "UTC",
        shape = JsonFormat.Shape.STRING
    )
    private Instant creationDate;

    @Column(
        nullable = false
    )
    @JsonFormat(
        pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'",
        timezone = "UTC",
        shape = JsonFormat.Shape.STRING
    )
    private Instant updateDate;

    // Adicioanr Relações com

    // Usuário, Categoria

}
