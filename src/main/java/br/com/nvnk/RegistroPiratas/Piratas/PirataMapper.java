package br.com.nvnk.RegistroPiratas.Piratas;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PirataMapper {

    PirataModel toModel(PirataDTO dto);

    @Mapping(target = "idTripulacao", source = "tripulacao.id")
    @Mapping(target = "idAkumanomi", source = "akumanomi.id")
    PirataDTO toDTO(PirataModel model);
}
