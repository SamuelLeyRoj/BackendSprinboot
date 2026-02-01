package com.safa.testspringboot.Controller;


import com.safa.testspringboot.Dto.ValoracionDto;
import com.safa.testspringboot.Repository.ValoracionRepository;
import com.safa.testspringboot.Service.UsuarioSesionService;
import com.safa.testspringboot.Service.ValoracionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/valoracion")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ValoracionController {

    private ValoracionService valoracionService;
    private UsuarioSesionService usuarioSesionService;

    @PostMapping("/crearValoracion/{idUsuario}/{idIntercambio}")
    public void guardarValoracion( @RequestBody ValoracionDto valoracion, @PathVariable Integer idUsuario, @PathVariable Integer idIntercambio) {
        valoracionService.guardarValoracion(valoracion,idUsuario,idIntercambio);
    }


}
