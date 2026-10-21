package com.cineflix.controller;

import com.cineflix.model.BibliotecaUsuario;
import com.cineflix.service.BibliotecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
@RequiredArgsConstructor
public class BibliotecaController {
    private final BibliotecaService bibliotecaService;

    @GetMapping("/{usuarioId}")
    public List<BibliotecaUsuario> listar(@PathVariable Long usuarioId) {
        return bibliotecaService.listar(usuarioId);
    }
}
