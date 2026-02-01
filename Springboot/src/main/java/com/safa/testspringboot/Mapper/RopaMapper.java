package com.safa.testspringboot.Mapper;

import com.safa.testspringboot.Dto.RopaDto;
import com.safa.testspringboot.Models.Ropa;
import org.mapstruct.Mapper;


import java.util.List;



@Mapper(componentModel = "spring")
public interface RopaMapper {


    RopaDto convertirADTO(Ropa ropa);
    Ropa convertirAEntity(RopaDto dto);


    List<RopaDto> convertirADTO(List<Ropa> dtos);

    List<Ropa> convertirAEntity(List<RopaDto> dtos);

}
