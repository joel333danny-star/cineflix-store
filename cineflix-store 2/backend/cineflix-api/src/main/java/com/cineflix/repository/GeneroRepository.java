package com.cineflix.repository;

import com.cineflix.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    Optional<Genero> findByNombreIgnoreCase(String nombre);
}
