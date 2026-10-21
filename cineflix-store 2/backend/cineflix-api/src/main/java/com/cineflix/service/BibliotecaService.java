package com.cineflix.service;

import com.cineflix.model.BibliotecaUsuario;
import com.cineflix.repository.BibliotecaUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibliotecaService {
    private final BibliotecaUsuarioRepository bibliotecaRepository;

    public List<BibliotecaUsuario> listar(Long usuarioId) {
        return bibliotecaRepository.findByUsuarioIdOrderByFechaAdquisicionDesc(usuarioId);
    }
}
