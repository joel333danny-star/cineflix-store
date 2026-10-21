package com.cineflix.repository;

import com.cineflix.model.BibliotecaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BibliotecaUsuarioRepository extends JpaRepository<BibliotecaUsuario, Long> {
    List<BibliotecaUsuario> findByUsuarioIdOrderByFechaAdquisicionDesc(Long usuarioId);
}
