package com.safa.testspringboot.Dto;

import com.safa.testspringboot.Models.Estilo;
import com.safa.testspringboot.Models.Talla;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RopaTopDto {

    private Integer idRopa;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "La talla no puede estar vacío")
    private Talla talla;   // enum
    @NotBlank(message = "El estilo no puede estar vacío")
    private Estilo estilo; // enum
    private Long numeroIntercambios;

    public RopaTopDto() {

    }
}