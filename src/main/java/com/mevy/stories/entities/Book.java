package com.mevy.stories.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_book")
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of="id")
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

    @Column(
        nullable = false
    )
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

    @ManyToOne
    private User author;

    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Chapter> chapters = new HashSet<>();

    @ManyToMany(mappedBy = "favorites")
    private Set<User> users = new HashSet<>();

}
