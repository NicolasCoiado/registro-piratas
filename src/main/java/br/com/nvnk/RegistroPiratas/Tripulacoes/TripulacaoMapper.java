package br.com.nvnk.RegistroPiratas.Tripulacoes;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TripulacaoMapper {
    TripulacaoModel toModel (TripulacaoDTO dto);
    TripulacaoDTO toDTO (TripulacaoModel model);
}
