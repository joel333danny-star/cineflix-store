package com.cineflix.repository;

import com.cineflix.model.CarritoItem;
import com.cineflix.model.TipoAdquisicion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {
    Optional<CarritoItem> findByCarritoIdAndPeliculaIdAndTipoAdquisicion(Long carritoId, Long peliculaId, TipoAdquisicion tipoAdquisicion);
}
