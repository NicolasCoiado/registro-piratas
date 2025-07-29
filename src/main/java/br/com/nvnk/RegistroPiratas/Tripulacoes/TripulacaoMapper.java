package br.com.nvnk.RegistroPiratas.Tripulacoes;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import br.com.nvnk.RegistroPiratas.Piratas.PirataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripulacaoMapper {

    @Autowired
    private PirataRepository pirataRepository;

    public TripulacaoModel toModel(TripulacaoDTO dto) {
        TripulacaoModel model = new TripulacaoModel();
        model.setId(dto.getId());
        model.setTitulo(dto.getTitulo());

        if (dto.getIdCapitao() != null) {
            PirataModel capitao = pirataRepository.findById(dto.getIdCapitao()).orElse(null);
            model.setCapitao(capitao);
        }

        if (dto.getIdsMembros() != null) {
            List<PirataModel> membros = dto.getIdsMembros().stream()
                    .map(id -> pirataRepository.findById(id).orElse(null))
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
            model.setMembros(membros);
        }

        return model;
    }

    public TripulacaoDTO toDTO(TripulacaoModel model) {
        TripulacaoDTO dto = new TripulacaoDTO();
        dto.setId(model.getId());
        dto.setTitulo(model.getTitulo());

        if (model.getCapitao() != null) {
            dto.setIdCapitao(model.getCapitao().getId());
        }

        if (model.getMembros() != null) {
            List<Long> membrosIds = model.getMembros().stream()
                    .map(PirataModel::getId)
                    .collect(Collectors.toList());
            dto.setIdsMembros(membrosIds);
        }

        return dto;
    }
}
