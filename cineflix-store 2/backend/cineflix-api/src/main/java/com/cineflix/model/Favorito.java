package com.cineflix.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "favoritos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "pelicula_id"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;
}
