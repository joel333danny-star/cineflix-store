package com.cineflix.repository;

import com.cineflix.model.Pelicula;
import com.cineflix.model.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findByGeneroNombreIgnoreCase(String genero);
    List<Pelicula> findByTipoProducto(TipoProducto tipoProducto);
    List<Pelicula> findTop10ByOrderByVistasDesc();
    List<Pelicula> findTop10ByOrderByRatingDesc();

    @Query("""
            SELECT p FROM Pelicula p
            WHERE LOWER(p.titulo) LIKE LOWER(CONCAT('%', :q, '%'))
               OR LOWER(p.genero.nombre) LIKE LOWER(CONCAT('%', :q, '%'))
               OR CAST(p.anio AS string) LIKE CONCAT('%', :q, '%')
               OR LOWER(p.director) LIKE LOWER(CONCAT('%', :q, '%'))
               OR LOWER(p.actorPrincipal) LIKE LOWER(CONCAT('%', :q, '%'))
            """)
    List<Pelicula> buscar(@Param("q") String q);
}
