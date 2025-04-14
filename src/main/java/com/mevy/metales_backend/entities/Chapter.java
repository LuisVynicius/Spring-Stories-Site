package com.mevy.metales_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "tb_chapter"
)
@Builder
@Getter
@Setter
public class Chapter {
    
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
        length = 50
    )
    private String name;

    @Column(
        length = 2000
    )
    private String content;

}
