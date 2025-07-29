package br.com.nvnk.RegistroPiratas.Tripulacoes;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import br.com.nvnk.RegistroPiratas.Piratas.PirataRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripulacaoService {
    @Autowired
    private TripulacaoMapper mapper;

    @Autowired
    private TripulacaoRepository tripulacaoRepository;

    @Autowired
    private PirataRepository pirataRepository;

    public TripulacaoDTO cadastrarTripulacao(TripulacaoDTO tripulacaoEnviada) {
        TripulacaoModel modelo = new TripulacaoModel();
        modelo.setTitulo(tripulacaoEnviada.getTitulo());

        if (tripulacaoEnviada.getIdCapitao() != null) {
            PirataModel capitao = pirataRepository.findById(tripulacaoEnviada.getIdCapitao())
                    .orElseThrow(() -> new EntityNotFoundException("Capitão não cadastrado."));
            modelo.setCapitao(capitao);
        }

        TripulacaoModel modeloSalvo = tripulacaoRepository.save(modelo);

        if (tripulacaoEnviada.getIdsMembros() != null) {
            List<PirataModel> membros = new ArrayList<>();
            for (Long idMembro : tripulacaoEnviada.getIdsMembros()) {
                PirataModel membro = pirataRepository.findById(idMembro)
                        .orElseThrow(() -> new EntityNotFoundException("Membro não cadastrado: ID " + idMembro));
                membro.setTripulacao(modeloSalvo);
                pirataRepository.save(membro);
                membros.add(membro);
            }
            modeloSalvo.setMembros(membros);
        }

        if (modeloSalvo.getCapitao() != null) {
            PirataModel capitao = modeloSalvo.getCapitao();
            capitao.setTripulacao(modeloSalvo);
            pirataRepository.save(capitao);
        }

        return mapper.toDTO(modeloSalvo);
    }

    public List<TripulacaoDTO> listarTripulacoes() {
        List<TripulacaoModel> tripulacoes = tripulacaoRepository.findAll();
        return tripulacoes.stream().map(mapper::toDTO).toList();
    }

    public TripulacaoDTO descreverTripulacao(Long id) {
        TripulacaoModel tripulacao = tripulacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tripulação não cadastrada."));
        return mapper.toDTO(tripulacao);
    }

    public TripulacaoDTO atualizarTripulacao(Long id, TripulacaoDTO tripulacaoEnviada) {
        TripulacaoModel tripulacaoCadastrada = tripulacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tripulação não cadastrada"));

        tripulacaoCadastrada.setTitulo(tripulacaoEnviada.getTitulo());

        if (tripulacaoEnviada.getIdCapitao() != null) {
            PirataModel capitao = pirataRepository.findById(tripulacaoEnviada.getIdCapitao())
                    .orElseThrow(() -> new EntityNotFoundException("Capitão não encontrado."));
            tripulacaoCadastrada.setCapitao(capitao);
            capitao.setTripulacao(tripulacaoCadastrada);
            pirataRepository.save(capitao);
        }

        if (tripulacaoEnviada.getIdsMembros() != null) {
            List<PirataModel> membros = new ArrayList<>();
            for (Long idMembro : tripulacaoEnviada.getIdsMembros()) {
                PirataModel membro = pirataRepository.findById(idMembro)
                        .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado: ID " + idMembro));
                membro.setTripulacao(tripulacaoCadastrada);
                pirataRepository.save(membro);
                membros.add(membro);
            }
            tripulacaoCadastrada.setMembros(membros);
        }

        TripulacaoModel salva = tripulacaoRepository.save(tripulacaoCadastrada);
        return mapper.toDTO(salva);
    }

    public TripulacaoDTO editarTripulacao(Long id, TripulacaoDTO edicoes) {
        TripulacaoModel tripulacaoCadastrada = tripulacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tripulação não encontrada"));

        if (edicoes.getTitulo() != null) {
            tripulacaoCadastrada.setTitulo(edicoes.getTitulo());
        }

        if (edicoes.getIdCapitao() != null) {
            PirataModel capitao = pirataRepository.findById(edicoes.getIdCapitao())
                    .orElseThrow(() -> new EntityNotFoundException("Capitão não encontrado."));
            tripulacaoCadastrada.setCapitao(capitao);
            capitao.setTripulacao(tripulacaoCadastrada);
            pirataRepository.save(capitao);
        }

        if (edicoes.getIdsMembros() != null) {
            List<PirataModel> membros = new ArrayList<>();
            for (Long idMembro : edicoes.getIdsMembros()) {
                PirataModel membro = pirataRepository.findById(idMembro)
                        .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado: ID " + idMembro));
                membro.setTripulacao(tripulacaoCadastrada);
                pirataRepository.save(membro);
                membros.add(membro);
            }
            tripulacaoCadastrada.setMembros(membros);
        }

        TripulacaoModel salva = tripulacaoRepository.save(tripulacaoCadastrada);
        return mapper.toDTO(salva);
    }

    public void deletarTripulacao(Long id) {
        TripulacaoModel existente = tripulacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tripulação não encontrada."));

        List<PirataModel> membros = existente.getMembros();

        for (PirataModel membro : membros){
            membro.setTripulacao(null);
            pirataRepository.save(membro);
        }

        tripulacaoRepository.delete(existente);
    }
}