package com.cineflix.dto;

import com.cineflix.model.TipoAdquisicion;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddCartRequest {
    @NotNull
    private Long usuarioId;
    @NotNull
    private Long peliculaId;
    @NotNull
    private TipoAdquisicion tipoAdquisicion;
}
