package com.cineflix.dto;

import com.cineflix.model.TipoAdquisicion;
import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CartItemResponse {
    private Long itemId;
    private Long peliculaId;
    private String titulo;
    private String posterUrl;
    private TipoAdquisicion tipoAdquisicion;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal totalLinea;
}
