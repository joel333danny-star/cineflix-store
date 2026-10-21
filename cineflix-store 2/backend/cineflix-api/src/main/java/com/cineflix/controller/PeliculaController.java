package com.cineflix.controller;

import com.cineflix.model.Pelicula;
import com.cineflix.model.TipoProducto;
import com.cineflix.service.PeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
@RequiredArgsConstructor
public class PeliculaController {
    private final PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> listar(@RequestParam(required = false) String genero,
                                 @RequestParam(required = false) TipoProducto tipo) {
        return peliculaService.listar(genero, tipo);
    }

    @GetMapping("/{id}")
    public Pelicula obtener(@PathVariable Long id) {
        return peliculaService.obtener(id);
    }

    @GetMapping("/buscar")
    public List<Pelicula> buscar(@RequestParam String q) {
        return peliculaService.buscar(q);
    }

    @GetMapping("/populares")
    public List<Pelicula> populares() {
        return peliculaService.populares();
    }

    @GetMapping("/mejor-calificadas")
    public List<Pelicula> mejorCalificadas() {
        return peliculaService.mejorCalificadas();
    }
}
