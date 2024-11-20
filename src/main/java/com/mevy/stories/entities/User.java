package com.mevy.stories.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tb_user")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        updatable = false
    )
    private Long id;

    @Column(
        length = 15,
        nullable = false
    )
    private String username;

    @Column(
        length = 100,
        nullable = false,
        unique = true
    )
    private String email;

    @Column(
        nullable = false
    )
    private String password;

    @Column(
        nullable = false
    )
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "user")
    private Set<Book> books = new HashSet<>();

    @OneToOne
    private UserInformation userInformation;
}
