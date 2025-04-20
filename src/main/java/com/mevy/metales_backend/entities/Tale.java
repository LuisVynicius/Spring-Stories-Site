package com.mevy.metales_backend.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mevy.metales_backend.entities.enums.TaleStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "tb_tale",
    indexes = @Index(columnList = "name")
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "name")
public class Tale {

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
        unique = true,
        length = 80
    )
    private String name;

    @JsonFormat(
        pattern = "yyyy-MM-dd'T'HH:mm::ss'Z'",
        timezone = "UTC",
        shape = JsonFormat.Shape.STRING
    )
    private Instant creationDate;
    
    @JsonFormat(
        pattern = "yyyy-MM-dd'T'HH:mm::ss'Z'",
        timezone = "UTC",
        shape = JsonFormat.Shape.STRING
    )
    private Instant deletationDate;

    @Column(
        length = 500
    )
    private String description;

    @Column(
        nullable = false
    )
    private Integer status;

    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    private User author;

    @ManyToMany(mappedBy = "likes")
    private Set<User> usersLikes = new HashSet<>();

    @ManyToMany(mappedBy = "favorites")
    private Set<User> usersFavorites = new HashSet<>();

    public void setStatus(TaleStatus status) {
        this.status = status.getCode();
    }

    public TaleStatus getStatus() {
        return TaleStatus.toEnum(this.status);
    }
}
