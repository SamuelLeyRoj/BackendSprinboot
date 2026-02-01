package com.safa.testspringboot.Service;

import com.safa.testspringboot.Dto.UsuarioSesionDto;
import com.safa.testspringboot.Exception.ElementoNoEncontradoException;
import com.safa.testspringboot.Exception.EliminarNoExistenteException;
import com.safa.testspringboot.Mapper.UsuarioMapper;
import com.safa.testspringboot.Models.UsuarioPerfil;
import com.safa.testspringboot.Models.UsuarioSesion;
import com.safa.testspringboot.Repository.UsuarioSesionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioSesionService {

    private UsuarioSesionRepository usuarioSesionRepository;

    @Qualifier("usuarioMapper")
    private UsuarioMapper mapper;

    public List<UsuarioSesionDto> obtenerTodos(){
        return mapper.convertirADTO(usuarioSesionRepository.findAll());
    }

    public UsuarioSesionDto getById(Integer id) {

        UsuarioSesion usuarioSesion = usuarioSesionRepository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoException("El usuario no existe"));

        return mapper.convertirADTO(usuarioSesion);
    }


    public void borrar(Integer id) {

        UsuarioSesion usuarioSesion = usuarioSesionRepository.findById(id).orElse(null);

        if (usuarioSesion == null) {

            throw  new EliminarNoExistenteException("No se puede eliminar el usuario");
        }else {
            usuarioSesionRepository.deleteById(id);
        }

    }

    public UsuarioSesionDto crearUsuarioConPerfil(UsuarioSesionDto dto) throws Exception {
        UsuarioSesion usuario = new UsuarioSesion();
        usuario.setNombre(dto.getNombre());
        if (dto.getEmail().contains("@")){
            usuario.setEmail(dto.getEmail());
        }else {
            throw new Exception("El Email no es valido");
        }

        usuario.setContrasenia(dto.getContrasenia());

        UsuarioPerfil perfil = new UsuarioPerfil();
        perfil.setDescripcion(dto.getDescripcion());
        perfil.setFotoPerfil(dto.getFotoPerfil());
        perfil.setUsuarioSesion(usuario);

        usuario.setPerfil(perfil);

        return mapper.convertirADTO(usuarioSesionRepository.save(usuario));
    }


    public UsuarioSesionDto consultarPorId(Integer id) {
        return mapper.convertirADTO(usuarioSesionRepository.findById(id).orElse(null));
    }

    public void borrarTodo(){
        usuarioSesionRepository.deleteAll();
    }

    public UsuarioSesionDto consultarPorNombre(String nombreBuscado) {
        return mapper.convertirADTO(usuarioSesionRepository.findByNombre(nombreBuscado).orElse(null));
    }
}
