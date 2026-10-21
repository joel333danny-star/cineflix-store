package com.cineflix.controller;

import com.cineflix.dto.AddCartRequest;
import com.cineflix.dto.CartResponse;
import com.cineflix.dto.UpdateQuantityRequest;
import com.cineflix.service.CarritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {
    private final CarritoService carritoService;

    @GetMapping("/{usuarioId}")
    public CartResponse obtener(@PathVariable Long usuarioId) {
        return carritoService.obtenerCarrito(usuarioId);
    }

    @PostMapping("/agregar")
    public CartResponse agregar(@Valid @RequestBody AddCartRequest request) {
        return carritoService.agregar(request.getUsuarioId(), request.getPeliculaId(), request.getTipoAdquisicion());
    }

    @PutMapping("/cantidad")
    public CartResponse actualizarCantidad(@Valid @RequestBody UpdateQuantityRequest request) {
        return carritoService.actualizarCantidad(request.getItemId(), request.getCantidad());
    }

    @DeleteMapping("/item/{itemId}")
    public CartResponse eliminar(@PathVariable Long itemId) {
        return carritoService.eliminarItem(itemId);
    }

    @DeleteMapping("/vaciar/{usuarioId}")
    public void vaciar(@PathVariable Long usuarioId) {
        carritoService.vaciar(usuarioId);
    }
}
