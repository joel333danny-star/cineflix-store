package com.cineflix.service;

import com.cineflix.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UsuarioRepository usuarioRepository;
    private final PeliculaRepository peliculaRepository;
    private final PedidoRepository pedidoRepository;
    private final ResenaRepository resenaRepository;

    public Map<String, Object> estadisticas() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("usuarios", usuarioRepository.count());
        data.put("peliculas", peliculaRepository.count());
        data.put("pedidos", pedidoRepository.count());
        data.put("resenas", resenaRepository.count());
        BigDecimal ventas = pedidoRepository.findAll().stream()
                .map(p -> p.getTotal() == null ? BigDecimal.ZERO : p.getTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        data.put("ventasTotales", ventas);
        data.put("masVistas", peliculaRepository.findTop10ByOrderByVistasDesc());
        data.put("mejorCalificadas", peliculaRepository.findTop10ByOrderByRatingDesc());
        return data;
    }
}
