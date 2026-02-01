package com.safa.testspringboot.services.Unitarios;

import com.safa.testspringboot.Dto.IntercambioDto;
import com.safa.testspringboot.Dto.RopaDto;
import com.safa.testspringboot.Dto.UsuarioSesionDto;
import com.safa.testspringboot.Dto.ValoracionDto;
import com.safa.testspringboot.Models.Estilo;
import com.safa.testspringboot.Models.Talla;
import com.safa.testspringboot.Service.IntercambioService;
import com.safa.testspringboot.Service.RopaService;
import com.safa.testspringboot.Service.UsuarioSesionService;
import com.safa.testspringboot.Service.ValoracionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RopaServiceTest {

    @Autowired
    private UsuarioSesionService usuarioService;

    @Autowired
    private RopaService ropaService;

    @Autowired
    private IntercambioService intercambioService;

    @Autowired
    private ValoracionService valoracionService;

    @BeforeAll
    void inicializarBaseDatos() throws Exception {

        // ----------------------------------------------------------------------------------
        // 1. CREAR 3 USUARIOS (IDs esperados: 1, 2, 3)
        // ----------------------------------------------------------------------------------

        UsuarioSesionDto user1 = new UsuarioSesionDto();
        user1.setNombre("Pepe");
        user1.setEmail("pepe@email.com");
        user1.setContrasenia("1234");
        user1.setDescripcion("Me gusta la ropa vintage");
        user1.setFotoPerfil("foto_pepe.jpg");
        usuarioService.crearUsuarioConPerfil(user1);

        UsuarioSesionDto user2 = new UsuarioSesionDto();
        user2.setNombre("Maria");
        user2.setEmail("maria@email.com");
        user2.setContrasenia("1234");
        user2.setDescripcion("Busco ropa deportiva");
        user2.setFotoPerfil("foto_maria.jpg");
        usuarioService.crearUsuarioConPerfil(user2);

        UsuarioSesionDto user3 = new UsuarioSesionDto();
        user3.setNombre("Juan");
        user3.setEmail("juan@email.com");
        user3.setContrasenia("1234");
        user3.setDescripcion("Solo intercambio formal");
        user3.setFotoPerfil("foto_juan.jpg");
        usuarioService.crearUsuarioConPerfil(user3);

        // ----------------------------------------------------------------------------------
        // 2. CREAR 3 PRENDAS DE ROPA (IDs esperados: 1, 2, 3)
        // ----------------------------------------------------------------------------------

        RopaDto ropa1 = new RopaDto();
        ropa1.setNombre("Camiseta Retro");
        ropa1.setEstilo(Estilo.CASUAL);
        ropa1.setTalla(Talla.M);
        ropa1.setFoto("img_cami.jpg");
        ropa1.setEstado("disponible");
        ropaService.crearRopa(ropa1, 1);

        RopaDto ropa2 = new RopaDto();
        ropa2.setNombre("Chándal Nike");
        ropa2.setEstilo(Estilo.DEPORTIVO);
        ropa2.setTalla(Talla.L);
        ropa2.setFoto("img_chandal.jpg");
        ropa2.setEstado("disponible");
        ropaService.crearRopa(ropa2, 2);

        RopaDto ropa3 = new RopaDto();
        ropa3.setNombre("Traje Boda");
        ropa3.setEstilo(Estilo.ELEGANTE);
        ropa3.setTalla(Talla.XL);
        ropa3.setFoto("img_traje.jpg");
        ropa3.setEstado("disponible");
        ropaService.crearRopa(ropa3, 1);

        // ----------------------------------------------------------------------------------
        // 3. CREAR 3 INTERCAMBIOS
        // ----------------------------------------------------------------------------------

        IntercambioDto inter1 = new IntercambioDto();
        inter1.setEstado("solicitado");
        intercambioService.crearIntercambio(inter1, 1, 2, 1);

        IntercambioDto inter2 = new IntercambioDto();
        inter2.setEstado("aceptado");
        intercambioService.crearIntercambio(inter2, 2, 3, 2);

        IntercambioDto inter3 = new IntercambioDto();
        inter3.setEstado("finalizado");
        intercambioService.crearIntercambio(inter3, 1, 3, 3);

        // ----------------------------------------------------------------------------------
        // 4. CREAR 3 VALORACIONES
        // ----------------------------------------------------------------------------------

        ValoracionDto val1 = new ValoracionDto();
        val1.setComentario("Todo perfecto, muy rápido");
        val1.setPuntuacion(5);
        valoracionService.guardarValoracion(val1, 2, 1);

        ValoracionDto val2 = new ValoracionDto();
        val2.setComentario("La ropa estaba un poco usada");
        val2.setPuntuacion(3);
        valoracionService.guardarValoracion(val2, 3, 2);

        ValoracionDto val3 = new ValoracionDto();
        val3.setComentario("El traje es increíble");
        val3.setPuntuacion(5);
        valoracionService.guardarValoracion(val3, 3, 3);
    }


        @Test
        @DisplayName("Test Negativo 3")
        public void crearPrendaSinTalla() throws Exception {

            RopaDto ropa = new RopaDto();
            ropa.setNombre("Traje Boda");
            ropa.setEstilo(Estilo.ELEGANTE);
            ropa.setTalla(null);
            ropa.setFoto("img_traje.jpg");
            ropa.setEstado("disponible");

            Exception exception = assertThrows(
                    Exception.class,
                    () -> ropaService.crearRopa(ropa, 1)
            );
            assertEquals(
                    "La talla es obligatoria para crear una prenda",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Test Positivo 3")
        public void crearPrendaConUsuarioExistente() throws Exception {

            RopaDto ropa = new RopaDto();
            ropa.setNombre("Traje Boda");
            ropa.setEstilo(Estilo.ELEGANTE);
            ropa.setTalla(Talla.XL);
            ropa.setFoto("img_traje.jpg");
            ropa.setEstado("disponible");

            ropaService.crearRopa(ropa, 1);

            List<RopaDto> lista =
                    new ArrayList<>(ropaService.obtenerTodos());

            assertEquals("Traje Boda", lista.get(3).getNombre());
        }


    @Test
    @DisplayName("Test Negativo 4 ")
    public void tallaIncorrecta() {

        List<RopaDto> lista = new ArrayList<>(ropaService.obtenerTodos());

        List<RopaDto> listaFiltrada = lista.stream()
                .filter(x -> x.getTalla().equals("Maripili"))
                .toList();

        assertThrows(RuntimeException.class, () -> {
            listaFiltrada.getFirst();
        });
    }

    @Test
    @DisplayName("Test Positivo 4 ")
    public void buscarTallaEspecial() {

        List<RopaDto> lista = new ArrayList<>(ropaService.obtenerTodos());

        List<RopaDto> listaFiltrada = lista.stream()
                .filter(x -> x.getTalla().equals(Talla.M))
                .toList();

        assertFalse(listaFiltrada.isEmpty());
    }

    @Test
    @DisplayName("Test Negativo 5")
    public void nombreNuloEditado() {

        RopaDto ropa = ropaService.getById(1);

        assertThrows(Exception.class, () -> {
            ropa.setNombre(null);
        });
    }

    @Test
    @DisplayName("Test Positivo 5")
    public void editarDetallesPrendaExistente() {

        RopaDto ropa1 = ropaService.getById(1);
        ropa1.setNombre("Camiseta Nike");
        ropa1.setTalla(Talla.XS);
        ropa1.setEstilo(Estilo.DEPORTIVO);

        assertEquals("Camiseta Nike", ropa1.getNombre());
        assertEquals(Talla.XS, ropa1.getTalla());
        assertEquals(Estilo.DEPORTIVO, ropa1.getEstilo());
    }


}
