    package com.safa.testspringboot.Controller;

    import com.safa.testspringboot.Repository.RopaRepository;
    import com.safa.testspringboot.Dto.RopaDto;
    import com.safa.testspringboot.Models.Estilo;
    import com.safa.testspringboot.Models.Ropa;
    import com.safa.testspringboot.Models.Talla;
    import com.safa.testspringboot.Service.RopaService;
    import jakarta.validation.Valid;
    import lombok.AllArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile; // Imprescindible para recibir archivos
    import java.nio.file.Path;                              // Para definir rutas
    import java.nio.file.Paths;                             // Para crear la ruta
    import java.nio.file.Files;                             // Para crear carpetas y copiar archivos
    import java.nio.file.StandardCopyOption;                // Para sobrescribir si el archivo ya existe
    import java.io.IOException;
    import java.nio.file.Files;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @RestController
    @RequestMapping("/ropa")
    @AllArgsConstructor
    @CrossOrigin(origins = "*")
    public class RopaController {

        private final RopaRepository ropaRepository;
        private final RopaService ropaService;

        // 1. Ruta para la carpeta de fotos
        private final Path root = Paths.get("src/main/resources/static/uploads");

        @GetMapping("/all")
        public List<RopaDto> obtenerTodas() {
            return ropaService.obtenerTodos();
        }

        // ESTE ES EL QUE SE QUEDA PARA LA FOTO
        @PostMapping("/subir")
        public ResponseEntity<?> subirImagen(@RequestParam("file") MultipartFile file) {
            try {
                if (!Files.exists(root)) {
                    Files.createDirectories(root);
                }

                String nombreArchivo = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                // Añadimos StandardCopyOption.REPLACE_EXISTING para evitar errores si el archivo ya existe
                Files.copy(file.getInputStream(), this.root.resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);

                Map<String, String> respuesta = new HashMap<>();
                respuesta.put("mensaje", "¡Foto guardada!");
                respuesta.put("nombre", nombreArchivo);

                return ResponseEntity.ok(respuesta);

            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error al guardar la imagen: " + e.getMessage());
            }
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

        // ELIMINADO: El segundo @PostMapping("/subir") que causaba el conflicto.
    }