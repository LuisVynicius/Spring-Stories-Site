package com.mevy.stories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_user")
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

}
