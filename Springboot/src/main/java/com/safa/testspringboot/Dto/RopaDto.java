package com.safa.testspringboot.Dto;

import com.safa.testspringboot.Models.Estilo;
import com.safa.testspringboot.Models.Talla;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RopaDto {

    private Integer id;

    @NotBlank(message = "El nombre de la prenda no puede estar vacío")
    private String nombre;

    @NotNull(message = "Debes indicar un estilo")
    private Estilo estilo;

    private String foto;

    @NotNull(message = "Debes indicar una talla")
    private Talla talla;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la prenda no puede ser nulo");
        }
        this.nombre = nombre;
    }
}
