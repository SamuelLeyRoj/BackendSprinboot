package com.safa.testspringboot.Service;

import com.safa.testspringboot.Dto.RopaDto;
import com.safa.testspringboot.Dto.UsuarioSesionDto;
import com.safa.testspringboot.Exception.ElementoNoEncontradoException;
import com.safa.testspringboot.Exception.EliminarNoExistenteException;
import com.safa.testspringboot.Mapper.RopaMapper;
import com.safa.testspringboot.Mapper.UsuarioMapper;
import com.safa.testspringboot.Models.Estilo;
import com.safa.testspringboot.Models.Ropa;
import com.safa.testspringboot.Models.Talla;
import com.safa.testspringboot.Models.UsuarioPerfil;
import com.safa.testspringboot.Repository.RopaRepository;
import com.safa.testspringboot.Repository.UsuarioPerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RopaService {

    private final RopaRepository ropaRepository;
    private final UsuarioPerfilRepository usuarioPerfilRepository;
    @Qualifier("ropaMapper")
    private final RopaMapper mapper;

    public List<RopaDto> obtenerTodos() {
        return mapper.convertirADTO(ropaRepository.findAll());
    }

    public RopaDto getById(Integer id) {
        Ropa ropa = ropaRepository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoException("Prenda no encontrada"));
        return mapper.convertirADTO(ropa);
    }

    public void crearRopa(RopaDto dto, Integer idUsuarioPerfil) throws Exception {
        UsuarioPerfil usuario = usuarioPerfilRepository.findById(idUsuarioPerfil)
                .orElseThrow(() -> new ElementoNoEncontradoException("Usuario no encontrado"));



        if (dto.getTalla() == null) {
            throw new Exception("La talla es obligatoria para crear una prenda");
        }
        Ropa ropa = mapper.convertirAEntity(dto);
        ropa.setUsuario(usuario);
        ropa.setEstado(dto.getEstado() != null ? dto.getEstado() : "disponible");
        ropaRepository.save(ropa);
    }

    // Filtrar
    public List<RopaDto> filtrar(Estilo estilo, Talla talla, String estado) {
        List<Ropa> lista = ropaRepository.filtrar(estilo, talla, estado);
        return mapper.convertirADTO(lista);
    }

    // Actualizar
    public RopaDto actualizarRopa(Integer id, RopaDto dto) {
        Ropa ropa = ropaRepository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoException("Prenda no encontrada"));

        if (dto.getNombre() != null) ropa.setNombre(dto.getNombre());
        if (dto.getEstilo() != null) ropa.setEstilo(dto.getEstilo());
        if (dto.getFoto() != null) ropa.setFoto(dto.getFoto());
        if (dto.getTalla() != null) ropa.setTalla(dto.getTalla());
        if (dto.getEstado() != null) ropa.setEstado(dto.getEstado());

        return mapper.convertirADTO(ropaRepository.save(ropa));
    }

    // Borrar
    public void borrar(Integer id) {
        if (!ropaRepository.existsById(id)) {
            throw new EliminarNoExistenteException("No se puede eliminar, prenda no encontrada");
        }
        ropaRepository.deleteById(id);
    }

    public void borrarTodo(){
        ropaRepository.deleteAll();
    }



}
