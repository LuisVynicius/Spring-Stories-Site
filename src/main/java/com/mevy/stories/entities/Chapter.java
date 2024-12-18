package com.mevy.stories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tb_chapter")
@Data
@Builder
public class Chapter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        updatable = false
    )
    private Long id;

    @Column(
        length = 50
    )
    private String title;

    @Column()
    @ElementCollection
    private String[] lines;

    @ManyToOne
    private Book book;
}
