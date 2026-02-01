    package com.safa.testspringboot.Controller;


    import com.safa.testspringboot.Dto.RopaDto;
    import com.safa.testspringboot.Models.Estilo;
    import com.safa.testspringboot.Models.Talla;
    import com.safa.testspringboot.Service.RopaService;
    import jakarta.validation.Valid;
    import lombok.AllArgsConstructor;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    @RestController
    @RequestMapping("/ropa")
    @AllArgsConstructor
    @CrossOrigin(origins = "http://localhost:4200")
    public class RopaController {

        private final RopaService ropaService;

        @GetMapping("/all")
        public List<RopaDto> obtenerTodas() {
            return ropaService.obtenerTodos();
        }

        @GetMapping("/{id}")
        public RopaDto obtenerPorId(@PathVariable Integer id) {
            return ropaService.getById(id);
        }

        @PostMapping("/crear/{idUsuario}")
        public void crearRopa(@PathVariable Integer idUsuario,@Valid @RequestBody RopaDto dto) throws Exception {
            ropaService.crearRopa(dto, idUsuario);
        }

        @PutMapping("/{id}")
        public RopaDto actualizarRopa(@PathVariable Integer id, @RequestBody RopaDto dto) {
            return ropaService.actualizarRopa(id, dto);
        }

        @DeleteMapping("/{id}")
        public void eliminarRopa(@PathVariable Integer id) {
            ropaService.borrar(id);
        }

        @GetMapping("/filtro")
        public List<RopaDto> filtrarRopa(
                @RequestParam(required = false) Estilo estilo,
                @RequestParam(required = false) Talla talla,
                @RequestParam(required = false) String estado
        ) {
            return ropaService.filtrar(estilo, talla, estado);
        }

        @DeleteMapping("Borrar/{id}")
        public void borrarRopa(@PathVariable Integer id) {
            ropaService.borrar(id);

        }
    }
