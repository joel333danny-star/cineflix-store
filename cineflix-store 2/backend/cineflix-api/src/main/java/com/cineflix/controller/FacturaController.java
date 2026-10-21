package com.cineflix.controller;

import com.cineflix.dto.FacturaResponse;
import com.cineflix.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturas")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaService facturaService;

    @GetMapping("/pedido/{pedidoId}")
    public FacturaResponse obtenerPorPedido(@PathVariable Long pedidoId) {
        return facturaService.obtenerPorPedido(pedidoId);
    }
}
