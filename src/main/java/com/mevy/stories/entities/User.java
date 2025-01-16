package com.mevy.stories.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mevy.stories.entities.enums.ProfileEnum;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_user_authorities")
    private Set<Integer> authorities = new HashSet<>();

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    @ManyToMany()
    @JsonIgnore
    private Set<Book> favorites = new HashSet<>();

    public Set<ProfileEnum> getAuthorities() {
        return this.authorities.stream().map(
            x -> ProfileEnum.valueOf(x)
        ).collect(Collectors.toSet());
    }

    public void addAuthoritie(ProfileEnum profile) {
        this.authorities.add(profile.getCode());
    }

}
