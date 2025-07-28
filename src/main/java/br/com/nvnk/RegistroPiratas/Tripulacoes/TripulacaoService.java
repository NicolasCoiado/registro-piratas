package br.com.nvnk.RegistroPiratas.Tripulacoes;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import br.com.nvnk.RegistroPiratas.Piratas.PirataRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripulacaoService {
    @Autowired
    private TripulacaoMapper mapper;

    @Autowired
    private TripulacaoRepository tripulacaoRepository;

    @Autowired
    private PirataRepository pirataRepository;

    public TripulacaoDTO cadastrarTripulacao(TripulacaoDTO tripulacaoEnviada) {
        PirataModel capitaoEnviado = pirataRepository.findById(tripulacaoEnviada.getCapitao().getId())
                .orElseThrow(() -> new EntityNotFoundException("Capitão não cadastrado."));

        List<PirataModel> membrosEnviados = tripulacaoEnviada.getMembros()
                .stream()
                .map(membro -> pirataRepository.findById(membro.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Membro não cadastrado: \n (ID): " + membro.getId())))
                .collect(Collectors.toList());

        TripulacaoModel modeloSalvo = tripulacaoRepository.save(mapper.toModel(tripulacaoEnviada));

        capitaoEnviado.setTripulacao(modeloSalvo);
        pirataRepository.save(capitaoEnviado);

        membrosEnviados.forEach(membro -> {
            membro.setTripulacao(modeloSalvo);
            pirataRepository.save(membro);
        });

        return mapper.toDTO(modeloSalvo);
    }

    public List<TripulacaoDTO> listarTripulacoes(){
        List<TripulacaoModel> tripulacoes = tripulacaoRepository.findAll();
        return tripulacoes.stream().map(mapper::toDTO).toList();
    }

    public TripulacaoDTO descreverTripulacao(Long id){
        TripulacaoModel tripulacao = tripulacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tripulação não cadastrada."));
        TripulacaoDTO tripulacaoDTO = mapper.toDTO(tripulacao);
        return tripulacaoDTO;
    }

    public TripulacaoDTO atualizarTripulacao(Long id, TripulacaoDTO tripulacaoAtualizada) {
        return tripulacaoAtualizada;
    }

    public TripulacaoDTO editarTripulacao(Long id, TripulacaoDTO edicoes) {
        return edicoes;
    }

    public void deletarTripulacao(Long id) {
        TripulacaoModel existente = tripulacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tripulação não encontrada."));
        tripulacaoRepository.delete(existente);
    }
}
