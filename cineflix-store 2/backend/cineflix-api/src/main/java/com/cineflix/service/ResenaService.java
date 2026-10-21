package com.cineflix.service;

import com.cineflix.model.Pelicula;
import com.cineflix.model.Resena;
import com.cineflix.model.Usuario;
import com.cineflix.repository.PeliculaRepository;
import com.cineflix.repository.ResenaRepository;
import com.cineflix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaService {
    private final ResenaRepository resenaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PeliculaRepository peliculaRepository;

    public Resena crear(Long usuarioId, Long peliculaId, Integer calificacion, String comentario) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Pelicula pelicula = peliculaRepository.findById(peliculaId).orElseThrow(() -> new RuntimeException("Película no encontrada"));
        return resenaRepository.save(Resena.builder()
                .usuario(usuario)
                .pelicula(pelicula)
                .calificacion(calificacion)
                .comentario(comentario)
                .fecha(LocalDateTime.now())
                .build());
    }

    public List<Resena> porPelicula(Long peliculaId) {
        return resenaRepository.findByPeliculaIdOrderByFechaDesc(peliculaId);
    }
}
