package br.com.nvnk.RegistroPiratas.Akumanomis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AkumanomiService {
    @Autowired
    private AkumanomiMapper mapper;
    @Autowired
    private AkumanomiRepository repository;

    public AkumanomiDTO cadastrarAkumanomi(AkumanomiDTO akumanomiDTO){
        AkumanomiModel akumanomi = mapper.toModel(akumanomiDTO);
        akumanomi = repository.save(akumanomi);
        return mapper.toDTO(akumanomi);
    }

    public List<AkumanomiDTO> listarAkumanomi(){
        List<AkumanomiModel> akumanomis = repository.findAll();
        return akumanomis.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public AkumanomiDTO descreverAkumanomi(Long id){
        Optional<AkumanomiModel> akumanomi = repository.findById(id);
        return akumanomi.map(mapper::toDTO).orElse(null);
    }

    public AkumanomiDTO atualizarAkumanomi(Long id, AkumanomiDTO akumanomiAtualizada){
        Optional<AkumanomiModel> akumanomiAtual = repository.findById(id);
        if (akumanomiAtual.isPresent()){
            AkumanomiModel akumanomiParaSubir = mapper.toModel(akumanomiAtualizada);
            akumanomiParaSubir.setId(id);
            AkumanomiModel akumanomiSalva = repository.save(akumanomiParaSubir);
            return mapper.toDTO(akumanomiSalva);
        }else{
            return null;
        }
    }

    public void deletarAkumanomi(Long id){repository.deleteById(id);}
}
