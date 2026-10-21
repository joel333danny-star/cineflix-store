package com.cineflix.controller;

import com.cineflix.model.Favorito;
import com.cineflix.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritoController {
    private final FavoritoService favoritoService;

    @GetMapping("/{usuarioId}")
    public List<Favorito> listar(@PathVariable Long usuarioId) {
        return favoritoService.listar(usuarioId);
    }

    @PostMapping("/{usuarioId}/{peliculaId}")
    public Favorito agregar(@PathVariable Long usuarioId, @PathVariable Long peliculaId) {
        return favoritoService.agregar(usuarioId, peliculaId);
    }

    @DeleteMapping("/{usuarioId}/{peliculaId}")
    public void eliminar(@PathVariable Long usuarioId, @PathVariable Long peliculaId) {
        favoritoService.eliminar(usuarioId, peliculaId);
    }
}
