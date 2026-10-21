package com.cineflix.repository;

import com.cineflix.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioId(Long usuarioId);
    Optional<Favorito> findByUsuarioIdAndPeliculaId(Long usuarioId, Long peliculaId);
    void deleteByUsuarioIdAndPeliculaId(Long usuarioId, Long peliculaId);
}
