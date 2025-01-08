package com.mevy.stories.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of="id")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        length = 50,
        nullable = false
    )
    private String username;
    
    @Column(
        length = 50,
        nullable = false,
        updatable = false,
        unique = true
    )
    private String email;

    @Column(
        nullable = false
    )
    private String password;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    @ManyToMany()
    @JsonIgnore
    private Set<Book> favorites = new HashSet<>();

}
