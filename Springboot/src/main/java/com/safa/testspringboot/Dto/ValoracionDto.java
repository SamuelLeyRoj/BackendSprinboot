package com.safa.testspringboot.Dto;

import com.safa.testspringboot.Models.Estilo;
import com.safa.testspringboot.Models.Talla;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionDto {

    @NotBlank(message = "El comentario no puede estar vacío")
    private String comentario;

    @NotNull(message = "Debes indicar una puntuación")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 10, message = "La puntuación máxima es 10")
    private Integer puntuacion;

    @NotNull(message = "Debes indicar el intercambio")
    private Integer intercambio;

    @NotNull(message = "Debes indicar el usuario")
    private Integer usuario;


    public void setComentario(String comentario) {
        if (comentario==null || comentario.length()==0) {
            throw new IllegalStateException("No se puede introducir una valoración nula");
        }
        this.comentario = comentario;
    }
}
