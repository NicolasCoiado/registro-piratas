package br.com.nvnk.RegistroPiratas.Akumanomis;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AkumanomiMapper {
    AkumanomiModel toModel(AkumanomiDTO dto);
    AkumanomiDTO toDTO(AkumanomiModel model);
}
