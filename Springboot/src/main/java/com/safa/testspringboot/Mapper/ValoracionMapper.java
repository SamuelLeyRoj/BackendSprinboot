package com.safa.testspringboot.Mapper;


import com.safa.testspringboot.Dto.ValoracionDto;
import com.safa.testspringboot.Models.Valoracion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ValoracionMapper {


    @Mapping(target = "intercambio", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Valoracion toEntity(ValoracionDto dto);

    @Mapping(source = "intercambio.id", target = "intercambio")
    @Mapping(source = "usuario.id", target = "usuario")
    ValoracionDto toDto(Valoracion entity);


    List<ValoracionDto> toDto(List<Valoracion> entities);
}
