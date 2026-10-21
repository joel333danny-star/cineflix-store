package com.cineflix.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResenaRequest {
    @NotNull
    private Long usuarioId;
    @NotNull
    private Long peliculaId;
    @Min(1) @Max(5)
    private Integer calificacion;
    @NotBlank
    private String comentario;
}
