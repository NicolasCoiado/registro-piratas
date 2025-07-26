package br.com.nvnk.RegistroPiratas.Alcunhas;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import br.com.nvnk.RegistroPiratas.Piratas.PirataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlcunhaService {
    @Autowired
    private AlcunhaRepository alcunhaRepository;

    @Autowired
    private PirataRepository pirataRepository;

    @Autowired
    private AlcunhaMapper mapper;

    public AlcunhaDTO cadastrarAlcunha(AlcunhaDTO alcunhaEnviada) {
        // Passo 1: Verifica e carrega os piratas válidos
        List<PirataModel> piratas = null;

        if (alcunhaEnviada.getPiratas() != null && !alcunhaEnviada.getPiratas().isEmpty()) {
            piratas = alcunhaEnviada.getPiratas().stream()
                    .map(pirata -> pirataRepository.findById(pirata.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Pirata com id " + pirata.getId() + " não encontrado.")))
                    .toList();
        }

        // Passo 2: Cria e salva a nova alcunha (sem relação ainda)
        AlcunhaModel alcunha = mapper.toModel(alcunhaEnviada);
        alcunha = alcunhaRepository.save(alcunha); // agora tem ID

        // Passo 3: Associa a alcunha a cada pirata e salva
        if (piratas != null) {
            for (PirataModel pirata : piratas) {
                pirata.getAlcunhas().add(alcunha); // adiciona a nova alcunha
                pirataRepository.save(pirata);     // salva o pirata atualizado
            }
        }

        // Passo 4: Converte e retorna DTO atualizado
        return mapper.toDTO(alcunha);
    }
}

