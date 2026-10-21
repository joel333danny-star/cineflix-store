package com.cineflix.service;

import com.cineflix.model.Favorito;
import com.cineflix.model.Pelicula;
import com.cineflix.model.Usuario;
import com.cineflix.repository.FavoritoRepository;
import com.cineflix.repository.PeliculaRepository;
import com.cineflix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PeliculaRepository peliculaRepository;

    public List<Favorito> listar(Long usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public Favorito agregar(Long usuarioId, Long peliculaId) {
        return favoritoRepository.findByUsuarioIdAndPeliculaId(usuarioId, peliculaId).orElseGet(() -> {
            Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Pelicula pelicula = peliculaRepository.findById(peliculaId).orElseThrow(() -> new RuntimeException("Película no encontrada"));
            return favoritoRepository.save(Favorito.builder().usuario(usuario).pelicula(pelicula).build());
        });
    }

    @Transactional
    public void eliminar(Long usuarioId, Long peliculaId) {
        favoritoRepository.deleteByUsuarioIdAndPeliculaId(usuarioId, peliculaId);
    }
}
