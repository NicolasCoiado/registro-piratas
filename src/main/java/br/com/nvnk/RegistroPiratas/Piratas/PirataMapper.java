package br.com.nvnk.RegistroPiratas.Piratas;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PirataMapper {
    PirataModel toModel (PirataDTO dto);
    PirataDTO toDTO (PirataModel model);
}
