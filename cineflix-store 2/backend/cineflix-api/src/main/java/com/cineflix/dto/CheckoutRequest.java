package com.cineflix.dto;

import com.cineflix.model.FormaPago;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CheckoutRequest {
    @NotNull
    private Long usuarioId;

    @NotNull
    private FormaPago formaPago;

    @NotBlank
    private String receptorNombre;

    @Email
    private String receptorEmail;
}
