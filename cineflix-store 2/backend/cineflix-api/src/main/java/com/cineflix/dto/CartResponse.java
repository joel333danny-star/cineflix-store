package com.cineflix.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CartResponse {
    private Long carritoId;
    private Long usuarioId;
    private List<CartItemResponse> items;
    private BigDecimal subtotal;
    private Integer totalItems;
}
