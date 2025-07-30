package br.com.nvnk.RegistroPiratas.Piratas;

import br.com.nvnk.RegistroPiratas.Akumanomis.AkumanomiModel;
import br.com.nvnk.RegistroPiratas.Akumanomis.AkumanomiRepository;
import br.com.nvnk.RegistroPiratas.Tripulacoes.TripulacaoModel;
import br.com.nvnk.RegistroPiratas.Tripulacoes.TripulacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PirataService {
    @Autowired
    private PirataRepository pirataRepository;

    @Autowired
    private AkumanomiRepository akumanomiRepository;

    @Autowired
    private TripulacaoRepository tripulacaoRepository;

    @Autowired
    private PirataMapper mapper;

    public PirataDTO cadastrarPirata (@RequestBody PirataDTO pirata) {
        PirataModel pirataModel = mapper.toModel(pirata);
        PirataModel pirataSalvo = pirataRepository.save(pirataModel);

        if (pirata.getIdAkumanomi() != null) {
            AkumanomiModel akumanomiSistema = akumanomiRepository.findById(pirata.getIdAkumanomi())
                    .orElseThrow(() -> new IllegalStateException("Akuma no mi não cadastrada"));
            akumanomiSistema.setUsuario(pirataSalvo);
            akumanomiRepository.save(akumanomiSistema);
        }

        if (pirata.getIdTripulacao() != null) {
            TripulacaoModel tripulacaoSistema = tripulacaoRepository.findById(pirata.getIdTripulacao())
                    .orElseThrow(() -> new IllegalStateException("Tripulação não cadastrada."));

            if (pirata.getCapitao() != null && pirata.getCapitao()) {
                if (tripulacaoSistema.getCapitao() == null) {
                    tripulacaoSistema.setCapitao(pirataSalvo);
                    tripulacaoRepository.save(tripulacaoSistema);
                } else {
                    throw new IllegalStateException("Esta tripulação já possui um capitão.");
                }
            } else {
                List<PirataModel> membros = tripulacaoSistema.getMembros();
                membros.add(pirataSalvo);
                tripulacaoSistema.setMembros(membros);
                tripulacaoRepository.save(tripulacaoSistema);
            }

            pirataSalvo.setTripulacao(tripulacaoSistema);
            pirataRepository.save(pirataSalvo);
        }

        return mapper.toDTO(pirataSalvo);
    }

    public List<PirataDTO> listarPiratas (){
        List<PirataModel> piratas = pirataRepository.findAll();
        List<PirataDTO> pirataDTOs = piratas.stream().map(mapper::toDTO).toList();
        return pirataDTOs;
    }

    public PirataDTO descreverPirata (Long id){
        PirataModel pirata = pirataRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pirata não cadastrado."));
        return mapper.toDTO(pirata);
    }

    public PirataDTO atualizarPirata(Long id, PirataDTO pirataComAtt) {
        PirataModel pirataExistente = pirataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pirata não cadastrado."));

        pirataComAtt.setId(id);

        if (pirataComAtt.getNome() != null) pirataExistente.setNome(pirataComAtt.getNome());
        if (pirataComAtt.getRecompensa() != null) pirataExistente.setRecompensa(pirataComAtt.getRecompensa());
        if (pirataComAtt.getImg_url() != null) pirataExistente.setImg_url(pirataComAtt.getImg_url());
        if (pirataComAtt.getDescricao() != null) pirataExistente.setDescricao(pirataComAtt.getDescricao());
        if (pirataComAtt.getAlcunhas() != null) pirataExistente.setAlcunhas(pirataComAtt.getAlcunhas());
        if (pirataComAtt.getVivo_morto() != null) pirataExistente.setVivo_morto(pirataComAtt.getVivo_morto());
        if (pirataComAtt.getCapitao() != null) pirataExistente.setCapitao(pirataComAtt.getCapitao());

        if (pirataComAtt.getIdAkumanomi() != null) {
            AkumanomiModel akumanomiAtt = akumanomiRepository.findById(pirataComAtt.getIdAkumanomi())
                    .orElseThrow(() -> new IllegalStateException("Akuma no mi não cadastrada"));

            if (akumanomiAtt.getUsuario() != null) {
                throw new IllegalStateException("Akuma no mi já possui um usuário.");
            }

            akumanomiAtt.setUsuario(pirataExistente);
            akumanomiRepository.save(akumanomiAtt);
            pirataExistente.setAkumanomi(akumanomiAtt);
        }

        if (pirataComAtt.getIdTripulacao() != null) {
            TripulacaoModel tripulacaoSistema = tripulacaoRepository.findById(pirataComAtt.getIdTripulacao())
                    .orElseThrow(() -> new IllegalStateException("Tripulação não cadastrada."));

            if (pirataComAtt.getCapitao() != null && pirataComAtt.getCapitao()) {
                if (tripulacaoSistema.getCapitao() != null &&
                        !tripulacaoSistema.getCapitao().getId().equals(pirataExistente.getId())) {
                    throw new IllegalStateException("Esta tripulação já possui um capitão.");
                }
                tripulacaoSistema.setCapitao(pirataExistente);
            } else {
                List<PirataModel> membros = tripulacaoSistema.getMembros();
                if (!membros.contains(pirataExistente)) {
                    membros.add(pirataExistente);
                    tripulacaoSistema.setMembros(membros);
                }
            }

            pirataExistente.setTripulacao(tripulacaoSistema);
            tripulacaoRepository.save(tripulacaoSistema);
        }

        PirataModel atualizado = pirataRepository.save(pirataExistente);

        return mapper.toDTO(atualizado);
    }

    public PirataDTO editarPirata(Long id, PirataDTO edicoes) {
        PirataModel pirataExistente = pirataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pirata não encontrado."));

        if (edicoes.getNome() != null) pirataExistente.setNome(edicoes.getNome());
        if (edicoes.getRecompensa() != null) pirataExistente.setRecompensa(edicoes.getRecompensa());
        if (edicoes.getImg_url() != null) pirataExistente.setImg_url(edicoes.getImg_url());
        if (edicoes.getDescricao() != null) pirataExistente.setDescricao(edicoes.getDescricao());
        if (edicoes.getAlcunhas() != null) pirataExistente.setAlcunhas(edicoes.getAlcunhas());
        if (edicoes.getVivo_morto() != null) pirataExistente.setVivo_morto(edicoes.getVivo_morto());

        if (edicoes.getIdAkumanomi() != null) {
            AkumanomiModel akumanomi = akumanomiRepository.findById(edicoes.getIdAkumanomi())
                    .orElseThrow(() -> new IllegalStateException("Akuma no Mi não cadastrada."));
            if (akumanomi.getUsuario() != null && !akumanomi.getUsuario().getId().equals(pirataExistente.getId())) {
                throw new IllegalStateException("Essa Akuma no Mi já possui um usuário.");
            }
            akumanomi.setUsuario(pirataExistente);
            akumanomiRepository.save(akumanomi);
            pirataExistente.setAkumanomi(akumanomi);
        }

        if (edicoes.getIdTripulacao() != null) {
            TripulacaoModel tripulacao = tripulacaoRepository.findById(edicoes.getIdTripulacao())
                    .orElseThrow(() -> new IllegalStateException("Tripulação não cadastrada."));

            if (edicoes.getCapitao() != null && edicoes.getCapitao()) {
                if (tripulacao.getCapitao() == null || tripulacao.getCapitao().getId().equals(pirataExistente.getId())) {
                    tripulacao.setCapitao(pirataExistente);
                } else {
                    throw new IllegalStateException("A tripulação já possui um capitão.");
                }
            } else {
                List<PirataModel> membros = tripulacao.getMembros();
                if (!membros.contains(pirataExistente)) {
                    membros.add(pirataExistente);
                }
                tripulacao.setMembros(membros);
            }

            pirataExistente.setTripulacao(tripulacao);
            tripulacaoRepository.save(tripulacao);
        }

        if (edicoes.getCapitao() != null) {
            pirataExistente.setCapitao(edicoes.getCapitao());
        }

        pirataRepository.save(pirataExistente);
        return mapper.toDTO(pirataExistente);
    }

    public void deletarPirata(Long id) {
        PirataModel pirata = pirataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pirata não encontrado."));

        AkumanomiModel akumanomi = pirata.getAkumanomi();
        if (akumanomi != null && akumanomi.getUsuario() != null && akumanomi.getUsuario().getId().equals(id)) {
            akumanomi.setUsuario(null);
            akumanomiRepository.save(akumanomi);
        }

        TripulacaoModel tripulacao = pirata.getTripulacao();
        if (tripulacao != null) {
            if (tripulacao.getCapitao() != null && tripulacao.getCapitao().getId().equals(id)) {
                tripulacao.setCapitao(null);
            }

            List<PirataModel> membros = tripulacao.getMembros();
            membros.removeIf(p -> p.getId().equals(id));
            tripulacao.setMembros(membros);

            tripulacaoRepository.save(tripulacao);
        }

        pirataRepository.delete(pirata);
    }
}
