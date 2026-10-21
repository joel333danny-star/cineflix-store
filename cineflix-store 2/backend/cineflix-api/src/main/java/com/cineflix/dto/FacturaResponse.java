package com.cineflix.dto;

import com.cineflix.model.FormaPago;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FacturaResponse {
    private Long facturaId;
    private String folio;
    private String emisorNombre;
    private String emisorRfc;
    private String receptorNombre;
    private String receptorEmail;
    private LocalDateTime fechaEmision;
    private FormaPago formaPago;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private List<FacturaDetalleResponse> detalles;
}
