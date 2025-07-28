package br.com.nvnk.RegistroPiratas.Piratas;

import br.com.nvnk.RegistroPiratas.Akumanomis.AkumanomiModel;
import br.com.nvnk.RegistroPiratas.Tripulacoes.TripulacaoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class PirataDTO {
    private Long id;
    private String nome;
    private BigDecimal recompensa;
    private String img_url;
    private String descricao;
    private String alcunhas;
    private Boolean vivo_morto;
    @JsonIgnore
    private TripulacaoModel tripulacao;
    private AkumanomiModel akumanomi;
}
