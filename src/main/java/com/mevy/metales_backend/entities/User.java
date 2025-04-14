package com.mevy.metales_backend.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.mevy.metales_backend.entities.enums.ProfileEnum;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "tb_user",
    indexes = @Index(columnList = "email")
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        updatable = false,
        unique = true
    )
    private Long id;

    @Column(
        nullable = false
    )
    private String username;
    
    @Column(
        nullable = false,
        updatable = false,
        unique = true,
        length = 50
    )
    private String email;

    @Column(
        nullable = false
    )
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_profile")
    private Set<Integer> profiles = new HashSet<>();

    public Set<ProfileEnum> getProfiles() {
        return profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(ProfileEnum profileEnum) {
        profiles.add(profileEnum.getCode());
    }
}
