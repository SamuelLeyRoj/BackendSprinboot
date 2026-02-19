package com.safa.testspringboot.services.Integracion;


import com.safa.testspringboot.Dto.*;
import com.safa.testspringboot.Mapper.IntercambioMapper;
import com.safa.testspringboot.Mapper.RopaMapper;
import com.safa.testspringboot.Mapper.UsuarioMapper;
import com.safa.testspringboot.Models.*;
import com.safa.testspringboot.Repository.IntercambioRepository;
import com.safa.testspringboot.Repository.RopaRepository;
import com.safa.testspringboot.Repository.UsuarioSesionRepository;
import com.safa.testspringboot.Service.IntercambioService;
import com.safa.testspringboot.Service.RopaService;
import com.safa.testspringboot.Service.UsuarioSesionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioSesionIntegrationTest {


    @InjectMocks
    UsuarioSesionService usuarioSesionService;

    @InjectMocks
    RopaService ropaService;
    @InjectMocks
    IntercambioService intercambioService;

    @Mock
    UsuarioSesionRepository usuarioSesionRepository;
    @Mock
    private IntercambioMapper intercambioMapper;
    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    RopaRepository ropaRepository;

    @Mock
    private RopaMapper ropaMapper;
    @Mock
    private IntercambioRepository intercambioRepository;



    @Test
    @DisplayName("Test Integración 1")
// 👉 Anotación de JUnit que indica que este método es un test.
// 👉 DisplayName permite mostrar un nombre más descriptivo en el reporte de tests.

    public void registrarUsuarioSesion() throws Exception {

        // ========================
        // GIVEN (Preparación)
        // ========================

        UsuarioSesionDto usuarioSesionDto = new UsuarioSesionDto();
        // 👉 Se crea el objeto DTO que simula los datos que enviaría un usuario.

        usuarioSesionDto.setNombre("samu");
        usuarioSesionDto.setEmail("samu@email");
        // 👉 Se cargan datos ficticios para el test.
        // 👉 Esto representa la entrada del sistema.

        Mockito.when(usuarioSesionRepository.save(Mockito.any(UsuarioSesion.class)))
                .thenReturn(new UsuarioSesion());
        // 👉 Aquí se MOCKEA el repositorio.
        // 👉 Significa: cuando se llame al método save con cualquier UsuarioSesion,
        // 👉 entonces devolverá un objeto UsuarioSesion vacío.

        // ❓ PREGUNTA DE EXAMEN:
        // ¿Por qué usamos Mockito.any(UsuarioSesion.class) en lugar de un objeto real?

        Mockito.when(this.usuarioMapper.convertirADTO(Mockito.any(UsuarioSesion.class)))
                .thenReturn(new UsuarioSesionDto());
        // 👉 Se mockea el mapper.
        // 👉 Cuando convierta una entidad UsuarioSesion a DTO, devolverá un DTO vacío.

        // ❓ PREGUNTA DE EXAMEN:
        // ¿Por qué necesitamos mockear el mapper en este test?
        // ¿Qué pasaría si no lo hacemos?

        // ========================
        // WHEN (Ejecución)
        // ========================

        this.usuarioSesionService.crearUsuarioConPerfil(usuarioSesionDto);
        // 👉 Se ejecuta el método que queremos probar.
        // 👉 Este método debería:
        //     1. Recibir el DTO
        //     2. Convertirlo a entidad
        //     3. Guardarlo en el repositorio
        //     4. Convertirlo nuevamente a DTO

        // ❓ PREGUNTA DE EXAMEN:
        // ¿Qué tipo de test es este? ¿Unitario o de integración? Justifica.

        // ========================
        // THEN (Verificación)
        // ========================

        Mockito.verify(usuarioSesionRepository).save(Mockito.any(UsuarioSesion.class));
        // 👉 Verifica que el repositorio fue llamado.
        // 👉 No verifica el resultado, solo que se ejecutó la interacción.

        // ❓ PREGUNTA DE EXAMEN:
        // ¿Cuál es la diferencia entre when() y verify() en Mockito?

        Mockito.verify(usuarioMapper).convertirADTO(Mockito.any(UsuarioSesion.class));
        // 👉 Verifica que el mapper fue utilizado para convertir la entidad a DTO.

        // ❓ PREGUNTA DE EXAMEN:
        // ¿Por qué es importante verificar las interacciones y no solo el resultado?
    }






    @Test
    @DisplayName("Test Integración 2")
    public void buscarPorNombreTest() {
        // Given
        String nombreBuscado = "Juan";

        UsuarioSesion usuarioMock = new UsuarioSesion();
        usuarioMock.setNombre(nombreBuscado);

        UsuarioSesionDto usuarioDtoMock = new UsuarioSesionDto();
        usuarioDtoMock.setNombre(nombreBuscado);
        Mockito.when(this.usuarioSesionRepository.findByNombre(Mockito.anyString()))
                .thenReturn(Optional.of(usuarioMock));


        Mockito.when(this.usuarioMapper.convertirADTO(Mockito.any(UsuarioSesion.class)))
                .thenReturn(usuarioDtoMock);

        // When
        UsuarioSesionDto resultado = this.usuarioSesionService.consultarPorNombre(nombreBuscado);

        assertNotNull(resultado);
        assertEquals(nombreBuscado, resultado.getNombre());
        // Then
        Mockito.verify(this.usuarioSesionRepository).findByNombre(nombreBuscado);
        Mockito.verify(this.usuarioMapper).convertirADTO(Mockito.any(UsuarioSesion.class));



    }


    @Test
    @DisplayName("Test Integración 3")
    public void crearRopaSinTalla() {

        // Given
        RopaDto ropaDto = new RopaDto();
        ropaDto.setNombre("Camiseta Jordan");
        ropaDto.setEstilo(Estilo.CASUAL);
        ropaDto.setTalla(null);


        Mockito.when(usuarioSesionRepository.findById(1))
                .thenReturn(Optional.of(new UsuarioSesion()));


        // When
        assertThrows(Exception.class, () -> {
            ropaService.crearRopa(ropaDto, 1);
        });

        // Then
        Mockito.verify(ropaRepository, Mockito.never())
                .save(Mockito.any(Ropa.class));

    }


    @Test
    @DisplayName("Test Integración 4")
    public void buscarPrendaTallaEspecifica() {

        // Given
        Ropa ropa1 = new Ropa();
        ropa1.setTalla(Talla.M);
        ropa1.setEstado("disponible");

        Ropa ropa2 = new Ropa();
        ropa2.setTalla(Talla.L);
        ropa2.setEstado("disponible");

        RopaDto dto1 = new RopaDto();
        dto1.setTalla(Talla.M);
        dto1.setEstado("disponible");

        RopaDto dto2 = new RopaDto();
        dto2.setTalla(Talla.L);
        dto2.setEstado("disponible");

        Mockito.when(ropaRepository.findAll())
                .thenReturn(List.of(ropa1, ropa2));

        Mockito.when(ropaMapper.convertirADTO(Mockito.anyList()))
                .thenReturn(List.of(dto1, dto2));

        // When
        List<RopaDto> lista = ropaService.obtenerTodos();

        List<RopaDto> filtrada = lista.stream()
                .filter(r -> r.getTalla() == Talla.M).toList();

        // Then

        assertEquals(1, filtrada.size());
        assertEquals(Talla.M, filtrada.get(0).getTalla());

        Mockito.verify(ropaRepository).findAll();
        Mockito.verify(ropaMapper).convertirADTO(Mockito.anyList());
    }


    @Test
    @DisplayName("Test Integración 5")
    public void editarRopa_existente() {

        // Given
        Ropa ropa = new Ropa();
        ropa.setNombre("Camisa");
        ropa.setTalla(Talla.M);

        RopaDto dto = new RopaDto();
        dto.setNombre("Camisa nueva");
        dto.setTalla(Talla.L);

        Mockito.when(ropaRepository.findById(1))
                .thenReturn(Optional.of(ropa));

        Mockito.when(ropaRepository.save(Mockito.any(Ropa.class)))
                .thenReturn(ropa);

        Mockito.when(ropaMapper.convertirADTO(Mockito.any(Ropa.class)))
                .thenReturn(dto);

        // When
        RopaDto resultado = ropaService.actualizarRopa(1, dto);

        // Then
        assertEquals("Camisa nueva", resultado.getNombre());
        assertEquals(Talla.L, resultado.getTalla());
    }


    @Test
    @DisplayName("Test Integracion 6")
    public void fechaAcuerdoNoAnterior() {

        // Given
        IntercambioDto dto = new IntercambioDto();
        dto.setFechaSolicitud(LocalDateTime.now());
        dto.setFechaAcuerdo(LocalDateTime.now().minusDays(1));

        // When / Then
        assertTrue(dto.getFechaAcuerdo().isBefore(dto.getFechaSolicitud()));
    }

    @Test
    @DisplayName("Test Integración 7")
    public void actualizarEstadoIntercambio() {

        // Given
        Intercambio intercambio = new Intercambio();
        intercambio.setEstado("solicitado");

        IntercambioDto dto = new IntercambioDto();
        dto.setEstado("aceptado");

        Mockito.when(intercambioRepository.findById(1))
                .thenReturn(Optional.of(intercambio));

        Mockito.when(intercambioRepository.save(Mockito.any(Intercambio.class)))
                .thenReturn(intercambio);

        Mockito.when(intercambioMapper.toDTO(Mockito.any(Intercambio.class)))
                .thenReturn(dto);

        // When
        IntercambioDto resultado =
                intercambioService.modificarEstado(1, "aceptado");

        // Then
        Mockito.verify(intercambioRepository).findById(1);
        Mockito.verify(intercambioRepository).save(Mockito.any(Intercambio.class));
        Mockito.verify(intercambioMapper).toDTO(Mockito.any(Intercambio.class));

        assertEquals("aceptado", resultado.getEstado());
    }


    @Test
    @DisplayName("Test Integración 8")
    public void valoracionComentarioNulo() {

        // Given
        ValoracionDto dto = new ValoracionDto();

        // When / Then
        assertThrows(Exception.class, () -> {
            dto.setComentario(null);
        });
    }


    @Test
    @DisplayName("Test Integracíon 9")
    public void masDeUnaPrendaEnTop() {

        // Given
        Mockito.when(intercambioRepository.findTopRopa(Mockito.any()))
                .thenReturn(List.of(new RopaTopDto(), new RopaTopDto()));

        // When
        List<RopaTopDto> resultado = intercambioService.getTop5RopaMasIntercambiada();
        assertTrue(resultado.size() > 1);

        // Then
        Mockito.verify(intercambioRepository).findTopRopa(Mockito.any());

    }


    @Test
    @DisplayName("Test Integración 10")
    public void usuarioConMasIntercambios() {

        // Given
        UsuarioMasAceptadoDto dto = new UsuarioMasAceptadoDto();
        dto.setNombre("Juan");

        Mockito.when(intercambioRepository.findUsuarioConMasIntercambiosAceptados())
                .thenReturn(dto);

        // When
        UsuarioMasAceptadoDto resultado = intercambioService.getUsuarioConMasIntercambiosAceptados();

        // Then
        Mockito.verify(intercambioRepository).findUsuarioConMasIntercambiosAceptados();
        assertEquals("Juan", resultado.getNombre());
    }







}
