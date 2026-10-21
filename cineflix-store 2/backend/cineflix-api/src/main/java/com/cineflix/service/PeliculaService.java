package com.cineflix.service;

import com.cineflix.model.Pelicula;
import com.cineflix.model.TipoProducto;
import com.cineflix.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeliculaService {
    private final PeliculaRepository peliculaRepository;

    public List<Pelicula> listar(String genero, TipoProducto tipo) {
        if (genero != null && !genero.isBlank()) {
            return peliculaRepository.findByGeneroNombreIgnoreCase(genero);
        }
        if (tipo != null) {
            return peliculaRepository.findByTipoProducto(tipo);
        }
        return peliculaRepository.findAll();
    }

    public List<Pelicula> buscar(String q) {
        if (q == null || q.isBlank()) return peliculaRepository.findAll();
        return peliculaRepository.buscar(q.trim());
    }

    public Pelicula obtener(Long id) {
        return peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));
    }

    public List<Pelicula> populares() {
        return peliculaRepository.findTop10ByOrderByVistasDesc();
    }

    public List<Pelicula> mejorCalificadas() {
        return peliculaRepository.findTop10ByOrderByRatingDesc();
    }
}
