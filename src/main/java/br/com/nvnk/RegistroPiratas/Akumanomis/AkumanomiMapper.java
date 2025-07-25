package br.com.nvnk.RegistroPiratas.Akumanomis;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import org.springframework.stereotype.Component;

@Component
public class AkumanomiMapper {
    public AkumanomiModel toModel(AkumanomiDTO dto, PirataModel usuario){
        AkumanomiModel model = new AkumanomiModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setTipo(dto.getTipo());
        model.setDescricao(dto.getDescricao());
        model.setImg_url(dto.getImg_url());
        model.setUsuario(usuario);
        return model;
    }

    public AkumanomiDTO toDTO(AkumanomiModel model){
        AkumanomiDTO dto = new AkumanomiDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setTipo(model.getTipo());
        dto.setDescricao(model.getDescricao());
        dto.setImg_url(model.getImg_url());

        PirataModel pirataModel = model.getUsuario();
        dto.setId_usuario(pirataModel.getId());

        return dto;
    }
}
