package com.cineflix.service;

import com.cineflix.dto.CheckoutResponse;
import com.cineflix.model.*;
import com.cineflix.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private static final BigDecimal IVA = new BigDecimal("0.16");

    private final UsuarioRepository usuarioRepository;
    private final CarritoRepository carritoRepository;
    private final PedidoRepository pedidoRepository;
    private final FacturaRepository facturaRepository;
    private final BibliotecaUsuarioRepository bibliotecaRepository;

    @Transactional
    public CheckoutResponse checkout(Long usuarioId, FormaPago formaPago, String receptorNombre, String receptorEmail) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        List<CarritoItem> items = carrito.getItems();

        if (items.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        BigDecimal subtotal = items.stream()
                .map(item -> item.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal impuestos = subtotal.multiply(IVA).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(impuestos).setScale(2, RoundingMode.HALF_UP);

        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .formaPago(formaPago)
                .estado(EstadoPedido.PAGADO)
                .subtotal(subtotal)
                .impuestos(impuestos)
                .total(total)
                .build();

        for (CarritoItem item : items) {
            PedidoDetalle detalle = PedidoDetalle.builder()
                    .pedido(pedido)
                    .pelicula(item.getPelicula())
                    .cantidad(item.getCantidad())
                    .tipoAdquisicion(item.getTipoAdquisicion())
                    .precioUnitario(item.getPrecioUnitario())
                    .totalLinea(item.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad())))
                    .build();

            pedido.getDetalles().add(detalle);
        }

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        for (CarritoItem item : items) {
            BibliotecaUsuario biblioteca = BibliotecaUsuario.builder()
                    .usuario(usuario)
                    .pelicula(item.getPelicula())
                    .tipoAdquisicion(item.getTipoAdquisicion())
                    .fechaAdquisicion(LocalDateTime.now())
                    .fechaExpiracion(
                            item.getTipoAdquisicion() == TipoAdquisicion.RENTA
                                    ? LocalDateTime.now().plusDays(7)
                                    : null
                    )
                    .descargaTemporal(item.getTipoAdquisicion() == TipoAdquisicion.RENTA)
                    .build();

            bibliotecaRepository.save(biblioteca);
        }

        Factura factura = Factura.builder()
                .pedido(pedidoGuardado)
                .folio("CFX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .emisorNombre("CineFlix Store S.A. de C.V.")
                .emisorRfc("CFS260616AB1")
                .receptorNombre(receptorNombre)
                .receptorEmail(receptorEmail)
                .fechaEmision(LocalDateTime.now())
                .subtotal(subtotal)
                .impuestos(impuestos)
                .total(total)
                .build();

        Factura facturaGuardada = facturaRepository.save(factura);

        carrito.getItems().clear();
        carritoRepository.save(carrito);

        return CheckoutResponse.builder()
                .pedidoId(pedidoGuardado.getId())
                .facturaId(facturaGuardada.getId())
                .folioFactura(facturaGuardada.getFolio())
                .mensaje("Pago procesado y factura generada correctamente")
                .build();
    }
}