package com.safa.testspringboot.Service;

import com.safa.testspringboot.Dto.ValoracionDto;
import com.safa.testspringboot.Exception.ElementoNoEncontradoException;
import com.safa.testspringboot.Mapper.ValoracionMapper;
import com.safa.testspringboot.Models.Intercambio;
import com.safa.testspringboot.Models.UsuarioPerfil;
import com.safa.testspringboot.Models.Valoracion;
import com.safa.testspringboot.Repository.IntercambioRepository;
import com.safa.testspringboot.Repository.UsuarioPerfilRepository;
import com.safa.testspringboot.Repository.ValoracionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ValoracionService {

    private final ValoracionRepository valoracionRepository;
    private final IntercambioRepository intercambioRepository;
    private final UsuarioPerfilRepository usuarioPerfilRepository;
    private final ValoracionMapper mapper;

    public List<ValoracionDto> getValoracion() {
        return mapper.toDto(valoracionRepository.findAll());
    }


    public void guardarValoracion(ValoracionDto valoracionDto, Integer idUsuario, Integer idIntercambio) {

        UsuarioPerfil usuarioPerfil = usuarioPerfilRepository.findById(idUsuario)
                .orElseThrow(() -> new ElementoNoEncontradoException("Usuario no encontrado"));

        Intercambio intercambio = intercambioRepository.findById(idIntercambio)
                .orElseThrow(() -> new ElementoNoEncontradoException("Intercambio no encontrado"));

        Valoracion valoracion = mapper.toEntity(valoracionDto);
        valoracion.setUsuario(usuarioPerfil);
        valoracion.setIntercambio(intercambio);

        valoracionRepository.save(valoracion);
    }

    public void borrarTodo(){
        valoracionRepository.deleteAll();
    }
}
