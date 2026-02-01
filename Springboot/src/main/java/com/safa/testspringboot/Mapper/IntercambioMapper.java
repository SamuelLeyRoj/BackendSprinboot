package com.safa.testspringboot.Mapper;

import com.safa.testspringboot.Dto.IntercambioDto;
import com.safa.testspringboot.Models.Intercambio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IntercambioMapper {

    @Mapping(target = "idUsuarioOfertante", ignore = true)
    @Mapping(target = "idUsuarioSolicitante", ignore = true)
    @Mapping(target = "idRopa", ignore = true)
    Intercambio toEntity(IntercambioDto dto);

    @Mapping(source = "idUsuarioOfertante.id", target = "idUsuarioOfertante")
    @Mapping(source = "idUsuarioSolicitante.id", target = "idUsuarioSolicitante")
    @Mapping(source = "idRopa.id", target = "idRopa")
    IntercambioDto toDTO(Intercambio entity);
    List<IntercambioDto> toDTOList(List<Intercambio> entities);
}



