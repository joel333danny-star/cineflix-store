package com.cineflix.service;

import com.cineflix.dto.CartItemResponse;
import com.cineflix.dto.CartResponse;
import com.cineflix.model.*;
import com.cineflix.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoService {
    private final UsuarioRepository usuarioRepository;
    private final PeliculaRepository peliculaRepository;
    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;

    @Transactional
    public CartResponse agregar(Long usuarioId, Long peliculaId, TipoAdquisicion tipoAdquisicion) {
        Carrito carrito = obtenerOCrearCarrito(usuarioId);
        Pelicula pelicula = peliculaRepository.findById(peliculaId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        BigDecimal precio = tipoAdquisicion == TipoAdquisicion.COMPRA
                ? pelicula.getPrecioCompra()
                : pelicula.getPrecioRenta();

        carritoItemRepository.findByCarritoIdAndPeliculaIdAndTipoAdquisicion(
                carrito.getId(), peliculaId, tipoAdquisicion
        ).ifPresentOrElse(item -> {
            item.setCantidad(item.getCantidad() + 1);
            carritoItemRepository.save(item);
        }, () -> {
            CarritoItem nuevo = CarritoItem.builder()
                    .carrito(carrito)
                    .pelicula(pelicula)
                    .tipoAdquisicion(tipoAdquisicion)
                    .cantidad(1)
                    .precioUnitario(precio)
                    .build();
            carritoItemRepository.save(nuevo);
        });

        return obtenerCarrito(usuarioId);
    }

    @Transactional(readOnly = true)
    public CartResponse obtenerCarrito(Long usuarioId) {
        Carrito carrito = obtenerOCrearCarrito(usuarioId);
        List<CarritoItem> items = new ArrayList<>(carrito.getItems());

        List<CartItemResponse> itemResponses = items.stream()
                .map(item -> CartItemResponse.builder()
                        .itemId(item.getId())
                        .peliculaId(item.getPelicula().getId())
                        .titulo(item.getPelicula().getTitulo())
                        .posterUrl(item.getPelicula().getPosterUrl())
                        .tipoAdquisicion(item.getTipoAdquisicion())
                        .cantidad(item.getCantidad())
                        .precioUnitario(item.getPrecioUnitario())
                        .totalLinea(item.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad())))
                        .build())
                .toList();

        BigDecimal subtotal = itemResponses.stream()
                .map(CartItemResponse::getTotalLinea)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalItems = itemResponses.stream().mapToInt(CartItemResponse::getCantidad).sum();

        return CartResponse.builder()
                .carritoId(carrito.getId())
                .usuarioId(usuarioId)
                .items(itemResponses)
                .subtotal(subtotal)
                .totalItems(totalItems)
                .build();
    }

    @Transactional
    public CartResponse actualizarCantidad(Long itemId, Integer cantidad) {
        CarritoItem item = carritoItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        if (cantidad <= 0) {
            Long usuarioId = item.getCarrito().getUsuario().getId();
            carritoItemRepository.delete(item);
            return obtenerCarrito(usuarioId);
        }
        item.setCantidad(cantidad);
        carritoItemRepository.save(item);
        return obtenerCarrito(item.getCarrito().getUsuario().getId());
    }

    @Transactional
    public CartResponse eliminarItem(Long itemId) {
        CarritoItem item = carritoItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        Long usuarioId = item.getCarrito().getUsuario().getId();
        carritoItemRepository.delete(item);
        return obtenerCarrito(usuarioId);
    }

    @Transactional
    public void vaciar(Long usuarioId) {
        Carrito carrito = obtenerOCrearCarrito(usuarioId);
        carrito.getItems().clear();
        carritoRepository.save(carrito);
    }

    public Carrito obtenerOCrearCarrito(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId).orElseGet(() -> {
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            return carritoRepository.save(Carrito.builder().usuario(usuario).items(new ArrayList<>()).build());
        });
    }
}
