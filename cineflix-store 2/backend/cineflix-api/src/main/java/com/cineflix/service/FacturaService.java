package com.cineflix.service;

import com.cineflix.dto.FacturaDetalleResponse;
import com.cineflix.dto.FacturaResponse;
import com.cineflix.model.Factura;
import com.cineflix.repository.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FacturaService {
    private final FacturaRepository facturaRepository;

    @Transactional(readOnly = true)
    public FacturaResponse obtenerPorPedido(Long pedidoId) {
        Factura factura = facturaRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        return FacturaResponse.builder()
                .facturaId(factura.getId())
                .folio(factura.getFolio())
                .emisorNombre(factura.getEmisorNombre())
                .emisorRfc(factura.getEmisorRfc())
                .receptorNombre(factura.getReceptorNombre())
                .receptorEmail(factura.getReceptorEmail())
                .fechaEmision(factura.getFechaEmision())
                .formaPago(factura.getPedido().getFormaPago())
                .subtotal(factura.getSubtotal())
                .impuestos(factura.getImpuestos())
                .total(factura.getTotal())
                .detalles(factura.getPedido().getDetalles().stream()
                        .map(d -> FacturaDetalleResponse.builder()
                                .descripcion(d.getPelicula().getTitulo())
                                .tipoAdquisicion(d.getTipoAdquisicion())
                                .cantidad(d.getCantidad())
                                .precioUnitario(d.getPrecioUnitario())
                                .totalLinea(d.getTotalLinea())
                                .build())
                        .toList())
                .build();
    }
}
