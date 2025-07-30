package br.com.nvnk.RegistroPiratas.Tripulacoes;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tripulacoes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TripulacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @OneToOne
    private PirataModel capitao;

    @OneToMany(mappedBy = "tripulacao")
    private List<PirataModel> membros;

    public TripulacaoModel() {}

    public TripulacaoModel(Long id, String titulo, PirataModel capitao, List<PirataModel> membros) {
        this.id = id;
        this.titulo = titulo;
        this.capitao = capitao;
        this.membros = membros;
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

    public PirataModel getCapitao() {
        return capitao;
    }

    public void setCapitao(PirataModel capitao) {
        this.capitao = capitao;
    }

    public List<PirataModel> getMembros() {
        return membros;
    }

    public void setMembros(List<PirataModel> membros) {
        this.membros = membros;
    }
}