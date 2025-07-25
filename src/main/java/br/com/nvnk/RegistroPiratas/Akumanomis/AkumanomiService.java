package br.com.nvnk.RegistroPiratas.Akumanomis;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import br.com.nvnk.RegistroPiratas.Piratas.PirataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AkumanomiService {
    @Autowired
    private AkumanomiMapper mapper;
    @Autowired
    private AkumanomiRepository akumanomiRepository;
    @Autowired
    private PirataRepository pirataRepository;


    public AkumanomiDTO cadastrarAkumanomi(AkumanomiDTO akumanomiDTO) {
        if (akumanomiDTO.getId_usuario() != null) {
            PirataModel usuario = pirataRepository.findById(akumanomiDTO.getId_usuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não cadastrado."));

            if (usuario.getAkumanomi() != null) {
                throw new IllegalStateException("O usuário já possui uma Akuma no Mi.");
            }

            AkumanomiModel akumanomiModel = mapper.toModel(akumanomiDTO, usuario);
            AkumanomiModel akumanomiSalva = akumanomiRepository.save(akumanomiModel);

            usuario.setAkumanomi(akumanomiSalva);
            pirataRepository.save(usuario);

            return mapper.toDTO(akumanomiSalva);
        }
        AkumanomiModel akumanomiModel = mapper.toModel(akumanomiDTO, null);
        AkumanomiModel akumanomiSalva = akumanomiRepository.save(akumanomiModel);
        return mapper.toDTO(akumanomiSalva);
    }

    public List<AkumanomiDTO> listarAkumanomis (){
        List<AkumanomiModel> akumanomisModel = akumanomiRepository.findAll();
        return akumanomisModel.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public AkumanomiDTO descreverAkumanomi (Long id){
        AkumanomiModel akumanomiModel = akumanomiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Akuma no mi não cadastrada!"));

        AkumanomiDTO akumanomiDTO = mapper.toDTO(akumanomiModel);

        return akumanomiDTO;
    }

    public AkumanomiDTO editarAkumanomi (Long id, AkumanomiDTO akumanomiEditada){
        AkumanomiModel akumanomiModel = akumanomiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Akuma no mi não cadastrada!"));

        if(akumanomiEditada.getId_usuario() != null){
            PirataModel usuario = pirataRepository.findById(akumanomiEditada.getId_usuario())
                    .orElseThrow(() -> new IllegalStateException("Usuário não cadastrado!"));

            if (akumanomiModel.getUsuario() == usuario){
                return
            }else{

            }
        }else{

        }

        AkumanomiDTO akumanomiEditadaDTO = akumanomiEditada;
        akumanomiEditadaDTO.setId(akumanomiModel.getId());

        return akumanomiEditadaDTO;
    }

}
