package com.safa.testspringboot.Service;

import com.safa.testspringboot.Dto.IntercambioDto;
import com.safa.testspringboot.Dto.RopaTopDto;
import com.safa.testspringboot.Dto.UsuarioMasAceptadoDto;
import com.safa.testspringboot.Exception.ElementoNoEncontradoException;
import com.safa.testspringboot.Mapper.IntercambioMapper;
import com.safa.testspringboot.Models.Intercambio;
import com.safa.testspringboot.Models.Ropa;
import com.safa.testspringboot.Models.UsuarioPerfil;
import com.safa.testspringboot.Repository.IntercambioRepository;
import com.safa.testspringboot.Repository.RopaRepository;
import com.safa.testspringboot.Repository.UsuarioPerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class IntercambioService {

    private final IntercambioRepository intercambioRepository;
    private final  UsuarioPerfilRepository usuarioPerfilRepository;
    private final RopaRepository ropaRepository;
    private final  IntercambioMapper mapper;

    public IntercambioDto crearIntercambio(IntercambioDto dto,Integer idUsuarioOfertante,Integer idUsuarioSolicitante,Integer idRopa) {

        UsuarioPerfil usuarioOfertante = usuarioPerfilRepository.findById(idUsuarioOfertante).orElse(null);
        UsuarioPerfil usuarioSolicitante = usuarioPerfilRepository.findById(idUsuarioSolicitante).orElse(null);
        Ropa ropa = ropaRepository.findById(idRopa).orElse(null);
        if (usuarioSolicitante == null||ropa==null||usuarioOfertante==null) {
            throw new ElementoNoEncontradoException("El usuario o prenda no se encuentra encontrado");
        }else {
            Intercambio intercambio = mapper.toEntity(dto);
            intercambio.setIdRopa(ropa);
            intercambio.setIdUsuarioSolicitante(usuarioSolicitante);
            intercambio.setIdUsuarioOfertante(usuarioOfertante);
            Intercambio guardado = intercambioRepository.save(intercambio);

            return mapper.toDTO(guardado);
        }

    }

    public IntercambioDto modificarEstado(Integer idIntercambio, String estado) {
        Intercambio intercambio = intercambioRepository.findById(idIntercambio)
                .orElse(null);

        if (intercambio==null) {
            throw new ElementoNoEncontradoException("El intercambio no existe");
        }else {
            intercambio.setEstado(estado);
            Intercambio actualizado = intercambioRepository.save(intercambio);

            return mapper.toDTO(actualizado);
        }

    }


    public List<RopaTopDto> getTop5RopaMasIntercambiada() {
        return intercambioRepository.findTopRopa(PageRequest.of(0, 5));
    }

    public UsuarioMasAceptadoDto getUsuarioConMasIntercambiosAceptados() {
        return intercambioRepository.findUsuarioConMasIntercambiosAceptados();
    }

    public void borrarTodo(){
        intercambioRepository.deleteAll();
    }

    public List<IntercambioDto> getIntercambios() {
        List<Intercambio> intercambios = intercambioRepository.findAll();
        return mapper.toDTOList(intercambios);
    }

}

