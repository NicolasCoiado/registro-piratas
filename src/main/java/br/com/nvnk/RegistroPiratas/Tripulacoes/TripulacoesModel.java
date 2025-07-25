package br.com.nvnk.RegistroPiratas.Tripulacoes;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tripulacoes")
public class TripulacoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @OneToOne
    @JoinColumn(name = "capitao_id")
    private PirataModel capitao;

    @OneToMany(mappedBy = "tripulacao")
    private List<PirataModel> membros;

    public TripulacoesModel() {
    }

    public TripulacoesModel(Long id, String titulo, PirataModel capitao, List<PirataModel> membros) {
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
