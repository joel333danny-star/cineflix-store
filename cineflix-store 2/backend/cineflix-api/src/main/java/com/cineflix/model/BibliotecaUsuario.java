package com.cineflix.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "biblioteca_usuario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BibliotecaUsuario {
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

    @Enumerated(EnumType.STRING)
    private TipoAdquisicion tipoAdquisicion;

    private LocalDateTime fechaAdquisicion;
    private LocalDateTime fechaExpiracion;
    private Boolean descargaTemporal;
}
