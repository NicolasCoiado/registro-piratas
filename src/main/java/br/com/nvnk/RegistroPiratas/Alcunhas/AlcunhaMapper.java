package br.com.nvnk.RegistroPiratas.Alcunhas;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlcunhaMapper {
    AlcunhaModel toModel(AlcunhaDTO dto);
    AlcunhaDTO toDTO(AlcunhaModel model);
}