package com.cineflix.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private Pedido pedido;

    @Column(unique = true)
    private String folio;

    private String emisorNombre;
    private String emisorRfc;
    private String receptorNombre;
    private String receptorEmail;
    private LocalDateTime fechaEmision;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal impuestos;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;
}
