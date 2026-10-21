package com.cineflix.controller;

import com.cineflix.dto.ResenaRequest;
import com.cineflix.model.Resena;
import com.cineflix.service.ResenaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
@RequiredArgsConstructor
public class ResenaController {
    private final ResenaService resenaService;

    @GetMapping("/pelicula/{peliculaId}")
    public List<Resena> porPelicula(@PathVariable Long peliculaId) {
        return resenaService.porPelicula(peliculaId);
    }

    @PostMapping
    public Resena crear(@Valid @RequestBody ResenaRequest request) {
        return resenaService.crear(request.getUsuarioId(), request.getPeliculaId(), request.getCalificacion(), request.getComentario());
    }
}
