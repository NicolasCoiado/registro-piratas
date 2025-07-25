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

        AkumanomiModel akumanomiModel;

        if (akumanomiDTO.getId_usuario() != null) {
            PirataModel usuario = pirataRepository.findById(akumanomiDTO.getId_usuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não cadastrado."));

            if (usuario.getAkumanomi() != null) {
                throw new IllegalStateException("O usuário já possui uma Akuma no Mi.");
            }

            akumanomiModel = mapper.toModel(akumanomiDTO, usuario);
            AkumanomiModel akumanomiSalva = akumanomiRepository.save(akumanomiModel);
            usuario.setAkumanomi(akumanomiSalva);
            pirataRepository.save(usuario);

            return mapper.toDTO(akumanomiSalva);

        } else {
            akumanomiModel = mapper.toModel(akumanomiDTO, null);
            AkumanomiModel akumanomiSalva = akumanomiRepository.save(akumanomiModel);
            return mapper.toDTO(akumanomiSalva);
        }
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

    public AkumanomiDTO atualizarAkumanomi (Long id, AkumanomiDTO akumanomiEditada){
        AkumanomiModel akumanomiCadastrada = akumanomiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Akuma no mi não cadastrada!"));

        if(akumanomiEditada.getId_usuario() != null){
            PirataModel novoUsuario = pirataRepository.findById(akumanomiEditada.getId_usuario())
                    .orElseThrow(() -> new IllegalStateException("Usuário não cadastrado!"));

            PirataModel antigoUsuario = akumanomiCadastrada.getUsuario();

            if (antigoUsuario == novoUsuario){
                akumanomiEditada.setId(akumanomiCadastrada.getId());
                AkumanomiModel editadaModel = mapper.toModel(akumanomiEditada, novoUsuario);
                akumanomiRepository.save(editadaModel);

                return akumanomiEditada;
            } else {
                if(antigoUsuario != null){
                    antigoUsuario.setAkumanomi(null);
                    pirataRepository.save(antigoUsuario);
                }

                akumanomiEditada.setId(akumanomiCadastrada.getId());
                AkumanomiModel editadaModel = mapper.toModel(akumanomiEditada, novoUsuario);

                novoUsuario.setAkumanomi(editadaModel);

                pirataRepository.save(novoUsuario);
                akumanomiRepository.save(editadaModel);

                return akumanomiEditada;
            }
        } else {
            PirataModel antigoUsuario = akumanomiCadastrada.getUsuario();
            if(antigoUsuario != null){
                antigoUsuario.setAkumanomi(null);
                pirataRepository.save(antigoUsuario);
            }

            akumanomiEditada.setId(akumanomiCadastrada.getId());
            AkumanomiModel editadaModel = mapper.toModel(akumanomiEditada, null);
            akumanomiRepository.save(editadaModel);

            return akumanomiEditada;
        }
    }

    public AkumanomiDTO editarAkumanomi(Long id, AkumanomiDTO atualizacoes) {
        AkumanomiModel akumanomi = akumanomiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Akuma no mi não cadastrada!"));

        if (atualizacoes.getNome() != null) {
            akumanomi.setNome(atualizacoes.getNome());
        }

        if (atualizacoes.getTipo() != null) {
            akumanomi.setTipo(atualizacoes.getTipo());
        }

        if (atualizacoes.getDescricao() != null) {
            akumanomi.setDescricao(atualizacoes.getDescricao());
        }

        if (atualizacoes.getImg_url() != null) {
            akumanomi.setImg_url(atualizacoes.getImg_url());
        }

        if (atualizacoes.getId_usuario() != null) {
            PirataModel novoUsuario = pirataRepository.findById(atualizacoes.getId_usuario())
                    .orElseThrow(() -> new IllegalStateException("Usuário não cadastrado!"));

            PirataModel antigoUsuario = akumanomi.getUsuario();

            if (antigoUsuario != null && !antigoUsuario.getId().equals(novoUsuario.getId())) {
                antigoUsuario.setAkumanomi(null);
                pirataRepository.save(antigoUsuario);
            }

            novoUsuario.setAkumanomi(akumanomi);
            akumanomi.setUsuario(novoUsuario);
            pirataRepository.save(novoUsuario);
        }

        akumanomiRepository.save(akumanomi);
        return mapper.toDTO(akumanomi);
    }

    public void deletarAkumanomi (Long id){
        AkumanomiModel akumanomiCadastrada = akumanomiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Akuma no mi não cadastrada!"));

        akumanomiRepository.deleteById(id);
    }
}
