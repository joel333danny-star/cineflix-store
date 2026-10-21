package com.cineflix.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateQuantityRequest {
    @NotNull
    private Long itemId;
    @Min(1)
    private Integer cantidad;
}
