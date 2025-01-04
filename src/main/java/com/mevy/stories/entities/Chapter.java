package com.mevy.stories.entities;

import java.time.Instant;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_chapter")
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of="id")
public class Chapter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String text;
    
    @Column(
        nullable = false
    )
    @JsonFormat(
        pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'",
        timezone = "UTC",
        shape = JsonFormat.Shape.STRING
    )
    private Instant creationDate;

    @ManyToOne
    private Book book;

}
