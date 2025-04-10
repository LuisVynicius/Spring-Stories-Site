package com.mevy.metales_backend.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "tb_userinfs"
)
public class UserInformations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        updatable = false,
        unique = true
    )
    private Long id;
    
    @Column(
    )
    private String fullname;

    @Column(
        nullable = false,
        updatable = false
    )
    private Instant create_at;
    
    @Column(
    )
    private Instant delete_at;

}
