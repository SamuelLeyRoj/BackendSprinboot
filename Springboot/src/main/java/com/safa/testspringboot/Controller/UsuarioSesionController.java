    package com.safa.testspringboot.Controller;

    import com.safa.testspringboot.Dto.UsuarioSesionDto;
    import com.safa.testspringboot.Service.UsuarioSesionService;
    import jakarta.validation.Valid;
    import lombok.AllArgsConstructor;
    import org.springframework.web.bind.annotation.*;
    import java.util.List;

    @RestController
    @RequestMapping("/usuarioSesion")
    @AllArgsConstructor
    public class UsuarioSesionController {

        private UsuarioSesionService usuarioSesionService;


        @GetMapping("/all")
        public List<UsuarioSesionDto> obtenerTodosUsuarios() {
            return usuarioSesionService.obtenerTodos();
        }

        @GetMapping("/consultar/{id}")
        public UsuarioSesionDto obtenerPorId( @PathVariable Integer id) {
            return usuarioSesionService.getById(id);
        }

        @DeleteMapping("/borrar/{id}")
        public void eliminarUsuario( @PathVariable Integer id) {
            usuarioSesionService.borrar(id);
        }

        @PostMapping("/crearUsuario")
        public UsuarioSesionDto crearUsuario(@Valid @RequestBody UsuarioSesionDto dto) throws Exception {
            return usuarioSesionService.crearUsuarioConPerfil(dto);
        }

    }
