package com.cineflix.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "peliculas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1200)
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioRenta;

    private Integer anio;
    private String director;
    private String actorPrincipal;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genero_id")
    private Genero genero;

    private Double rating;
    private Integer vistas;
    private Boolean premium;

    private String posterUrl;
    private String trailerUrl;
}
