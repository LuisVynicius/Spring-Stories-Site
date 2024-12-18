package com.mevy.stories.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tb_book")
@Data
@Builder
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        updatable = false
    )
    private Long id;

    @Column(
        length = 30,
        nullable = false
    )
    private String title;
    
    @Column(
        length = 255
    )
    private String description;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "books")
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Chapter> chapters = new HashSet<>();
}
