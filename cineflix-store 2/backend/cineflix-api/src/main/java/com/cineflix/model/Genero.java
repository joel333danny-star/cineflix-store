package com.cineflix.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "generos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;
}
