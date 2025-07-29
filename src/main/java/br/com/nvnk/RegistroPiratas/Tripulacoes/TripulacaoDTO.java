package br.com.nvnk.RegistroPiratas.Tripulacoes;

import java.util.List;

public class TripulacaoDTO {
    private Long id;
    private String titulo;
    private Long idCapitao;
    private List<Long> idsMembros;

    public TripulacaoDTO() {}

    public TripulacaoDTO(Long id, String titulo, Long idCapitao, List<Long> idsMembros) {
        this.id = id;
        this.titulo = titulo;
        this.idCapitao = idCapitao;
        this.idsMembros = idsMembros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getIdCapitao() {
        return idCapitao;
    }

    public void setIdCapitao(Long idCapitao) {
        this.idCapitao = idCapitao;
    }

    public List<Long> getIdsMembros() {
        return idsMembros;
    }

    public void setIdsMembros(List<Long> idsMembros) {
        this.idsMembros = idsMembros;
    }

    @Override
    public String toString() {
        return "TripulacaoDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idCapitao=" + idCapitao +
                ", idsMembros=" + idsMembros +
                '}';
    }
}