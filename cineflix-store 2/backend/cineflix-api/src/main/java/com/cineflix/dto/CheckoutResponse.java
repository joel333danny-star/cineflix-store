package com.cineflix.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CheckoutResponse {
    private Long pedidoId;
    private Long facturaId;
    private String folioFactura;
    private String mensaje;
}
