package com.pizzashop.demo.dtos;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

public record OrderDto(
        Long id,
        @NotEmpty(message = "La dirección no puede estar vacía")
        @NotNull(message = "La dirección no puede estar vacía")
        String address,
        @NotNull(message = "El telefono no puede estar vacío")
        @NotEmpty(message = "El telefono no puede estar vacío")
        String phone,
        @NotNull(message = "La dirección de email no puede estar vacía")
        @NotEmpty(message = "La dirección de email no puede estar vacía")
        String email,
        @NotNull(message = "La hora del pedido no puede estar vacía")
        LocalTime time,
        @NotNull(message = "La orden no puede estar vacia")
        @NotEmpty(message = "La orden no puede estar vacia")
        List<DetailsDto> details
){}
