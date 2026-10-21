package com.cineflix.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    private Integer cantidad;

    @Enumerated(EnumType.STRING)
    private TipoAdquisicion tipoAdquisicion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalLinea;
}
