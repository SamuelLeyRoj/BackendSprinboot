package com.safa.testspringboot.Mapper;

import com.safa.testspringboot.Dto.RopaDto;
import com.safa.testspringboot.Dto.UsuarioSesionDto;
import com.safa.testspringboot.Models.Ropa;
import com.safa.testspringboot.Models.UsuarioSesion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioSesionDto convertirADTO(UsuarioSesion usuarioSesion);
    UsuarioSesion convertirAEntity(UsuarioSesionDto dto);

    List<UsuarioSesionDto> convertirADTO(List<UsuarioSesion> dtos);
    List<UsuarioSesion> convertirAEntity(List<UsuarioSesionDto> dtos);
}

