package com.safa.testspringboot.services.Unitarios;

import com.safa.testspringboot.Dto.*;
import com.safa.testspringboot.Exception.ElementoNoEncontradoException;
import com.safa.testspringboot.Models.Estilo;
import com.safa.testspringboot.Models.Talla;
import com.safa.testspringboot.Service.IntercambioService;
import com.safa.testspringboot.Service.RopaService;
import com.safa.testspringboot.Service.UsuarioSesionService;
import com.safa.testspringboot.Service.ValoracionService;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class ValoracionServiceTest {

    @Autowired
    UsuarioSesionService usuarioService;

    @Autowired
    RopaService ropaService;

    @Autowired
    IntercambioService intercambioService;

    @Autowired
    ValoracionService valoracionService;

    @BeforeAll
    void inicializarBaseDatos() throws Exception {

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

        IntercambioDto inter1 = new IntercambioDto();
        inter1.setEstado("solicitado");
        intercambioService.crearIntercambio(inter1, 1, 2, 1);

        IntercambioDto inter2 = new IntercambioDto();
        inter2.setEstado("aceptado");
        intercambioService.crearIntercambio(inter2, 2, 3, 2);

        IntercambioDto inter3 = new IntercambioDto();
        inter3.setEstado("finalizado");
        intercambioService.crearIntercambio(inter3, 1, 3, 3);

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
    @DisplayName("Test Negativo 8")
    public void valoracionComentarioNulo() {

        ValoracionDto val67 = new ValoracionDto();
        val67.setComentario("La ropa estaba un poco sucia");
        valoracionService.guardarValoracion(val67, 2, 1);

        assertThrows(Exception.class, () -> {
            val67.setComentario(null);
        });
    }

    @Test
    @DisplayName("Test Positivo 8")
    public void valoracionDatosValidos() {

        ValoracionDto val8 = new ValoracionDto();
        val8.setPuntuacion(5);
        val8.setComentario("Muy bueno muy bueno");
        valoracionService.guardarValoracion(val8, 2, 1);

        List<ValoracionDto> lista9 =
                new ArrayList<>(valoracionService.getValoracion());

        assertEquals("Muy bueno muy bueno", lista9.get(3).getComentario());
    }

    @Test
    @DisplayName("Test Negativo 9")
    public void topPrendasSinRopa() {

        valoracionService.borrarTodo();
        intercambioService.borrarTodo();
        ropaService.borrarTodo();
        usuarioService.borrarTodo();

        List<RopaTopDto> resultado =
                intercambioService.getTop5RopaMasIntercambiada();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Test Positivo 9")
    public void topPrendasmasDeUnaPrenda() throws Exception {

        IntercambioDto inter4 = new IntercambioDto();
        inter4.setEstado("aceptado");
        intercambioService.crearIntercambio(inter4, 1, 2, 1);

        IntercambioDto inter5 = new IntercambioDto();
        inter5.setEstado("aceptado");
        intercambioService.crearIntercambio(inter5, 2, 3, 2);

        IntercambioDto inter6 = new IntercambioDto();
        inter6.setEstado("aceptado");
        intercambioService.crearIntercambio(inter6, 3, 1, 3);

        List<RopaTopDto> topPrendas =
                intercambioService.getTop5RopaMasIntercambiada();

        assertNotNull(topPrendas);
        assertTrue(topPrendas.size() > 1);
    }

    @Test
    @DisplayName("Test Negativo 10")
    public void usuarioInexistente() {

        assertThrows(ElementoNoEncontradoException.class, () -> {
            usuarioService.getById(99);
        });
    }

    @Test
    @DisplayName("Test Positivo 10")
    public void usuarioConMasIntercambios() {

        UsuarioMasAceptadoDto resultado =
                intercambioService.getUsuarioConMasIntercambiosAceptados();

        assertEquals("Maria", resultado.getNombre());
    }
}
