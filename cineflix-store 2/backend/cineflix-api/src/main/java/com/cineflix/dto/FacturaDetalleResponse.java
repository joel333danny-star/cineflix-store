package com.cineflix.dto;

import com.cineflix.model.TipoAdquisicion;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FacturaDetalleResponse {
    private String descripcion;
    private TipoAdquisicion tipoAdquisicion;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal totalLinea;
}
