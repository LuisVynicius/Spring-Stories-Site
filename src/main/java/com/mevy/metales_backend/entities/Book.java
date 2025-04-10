package com.mevy.metales_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity
@Table(
    name = "tb_book",
    indexes = @Index(columnList = "name")
)
@EqualsAndHashCode(of = "name")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        updatable = false,
        unique = true
    )
    private Long id;

    @Column(
        nullable = false,
        unique = true,
        length = 80
    )
    private String name;

    @Column(
        length = 500
    )
    private String description;
}
