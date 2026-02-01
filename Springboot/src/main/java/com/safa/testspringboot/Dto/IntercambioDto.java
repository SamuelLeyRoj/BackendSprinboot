package com.safa.testspringboot.Dto;

import com.safa.testspringboot.Models.Estilo;
import com.safa.testspringboot.Models.Talla;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntercambioDto {

    @NotNull(message = "Debes indicar un usuario ofertante")
    private Integer idUsuarioOfertante;

    @NotNull(message = "Debes indicar un usuario solicitante")
    private Integer idUsuarioSolicitante;

    @NotNull(message = "Debes indicar la prenda")
    private Integer idRopa;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    public void setEstado(String nuevoEstado) {
        if ("finalizado".equalsIgnoreCase(this.estado)) {
            throw new IllegalStateException("No se puede cambiar el estado de un intercambio ya finalizado");
        }
        this.estado = nuevoEstado;
    }
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaAcuerdo;
}
