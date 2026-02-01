package com.safa.testspringboot.Controller;

import com.safa.testspringboot.Dto.IntercambioDto;
import com.safa.testspringboot.Dto.RopaTopDto;
import com.safa.testspringboot.Dto.UsuarioMasAceptadoDto;
import com.safa.testspringboot.Service.IntercambioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/intercambios")
@RequiredArgsConstructor

public class IntercambioController {

    private final IntercambioService service;

    @PostMapping("/crear/{idUsuarioOfertante}/{idUsuarioSolicitante}/{idRopa}")
    public IntercambioDto crear(@RequestBody IntercambioDto dto,
                                @PathVariable Integer idUsuarioOfertante,
                                @PathVariable Integer idUsuarioSolicitante,
                                @PathVariable Integer idRopa
    ) {

        return service.crearIntercambio(dto, idUsuarioOfertante, idUsuarioSolicitante, idRopa);

    }



    @PatchMapping("/modificar/{idIntercambio}/{estado}")
    public IntercambioDto modificarEstado(@PathVariable Integer idIntercambio,
                                          @PathVariable String estado){


        return service.modificarEstado(idIntercambio, estado);
    }



    @GetMapping("/top-ropa")
    public List<RopaTopDto> top5Ropa() {
        return service.getTop5RopaMasIntercambiada();
    }


    @GetMapping("UsuarioMasIntercambios")
    public UsuarioMasAceptadoDto getUsuarioMasIntercambios() {
        return service.getUsuarioConMasIntercambiosAceptados();
    }
}
