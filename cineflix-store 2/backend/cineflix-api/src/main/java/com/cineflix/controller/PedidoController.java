package com.cineflix.controller;

import com.cineflix.dto.CheckoutRequest;
import com.cineflix.dto.CheckoutResponse;
import com.cineflix.model.Pedido;
import com.cineflix.repository.PedidoRepository;
import com.cineflix.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final CheckoutService checkoutService;
    private final PedidoRepository pedidoRepository;

    @PostMapping("/checkout")
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(
                request.getUsuarioId(),
                request.getFormaPago(),
                request.getReceptorNombre(),
                request.getReceptorEmail()
        );
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> porUsuario(@PathVariable Long usuarioId) {
        return pedidoRepository.findByUsuarioIdOrderByFechaDesc(usuarioId);
    }
}
