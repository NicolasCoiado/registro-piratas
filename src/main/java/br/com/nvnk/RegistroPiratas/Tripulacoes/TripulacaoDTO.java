package br.com.nvnk.RegistroPiratas.Tripulacoes;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class TripulacaoDTO {
    private Long id;
    private String titulo;
    @JsonIgnore
    private PirataModel capitao;
    @JsonIgnore
    private List<PirataModel> membros;

    public TripulacaoDTO() {
    }

    public TripulacaoDTO(Long id, String titulo, PirataModel capitao, List<PirataModel> membros) {
        this.id = id;
        this.titulo = titulo;
        this.capitao = capitao;
        this.membros = membros;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public PirataModel getCapitao() {
        return capitao;
    }

    public List<PirataModel> getMembros() {
        return membros;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCapitao(PirataModel capitao) {
        this.capitao = capitao;
    }

    public void setMembros(List<PirataModel> membros) {
        this.membros = membros;
    }
}
