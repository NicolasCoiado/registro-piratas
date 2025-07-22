package br.com.nvnk.RegistroPiratas.Piratas;

import br.com.nvnk.RegistroPiratas.Akumanomis.AkumanomiModel;
import br.com.nvnk.RegistroPiratas.Alcunhas.AlcunhaModel;
import br.com.nvnk.RegistroPiratas.Tripulacoes.TripulacoesModel;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "piratas")
public class PirataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "recompensa", precision = 12, scale = 2)
    private BigDecimal recompensa;

    @Column(name = "img_url")
    private String url_img;

    @ManyToOne
    @JoinColumn(name = "id_tripulacao")
    private TripulacoesModel tripulacao;

    @ManyToMany
    @JoinTable(
            name = "pirata_alcunha",
            joinColumns = @JoinColumn(name = "pirata_id"),
            inverseJoinColumns = @JoinColumn(name = "alcunha_id")
    )
    private List<AlcunhaModel> alcunhas = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_akumanomi")
    private AkumanomiModel akumanomi;

    public PirataModel() {
    }

    public PirataModel(Long id, String nome, BigDecimal recompensa, String url_img, TripulacoesModel tripulacao, List<AlcunhaModel> alcunhas, AkumanomiModel akumanomi) {
        this.id = id;
        this.nome = nome;
        this.recompensa = recompensa;
        this.url_img = url_img;
        this.tripulacao = tripulacao;
        this.alcunhas = alcunhas;
        this.akumanomi = akumanomi;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getRecompensa() {
        return recompensa;
    }

    public String getUrl_img() {
        return url_img;
    }

    public TripulacoesModel getTripulacao() {
        return tripulacao;
    }

    public List<AlcunhaModel> getAlcunhas() {
        return alcunhas;
    }

    public AkumanomiModel getAkumanomi() {
        return akumanomi;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRecompensa(BigDecimal recompensa) {
        this.recompensa = recompensa;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public void setTripulacao(TripulacoesModel tripulacao) {
        this.tripulacao = tripulacao;
    }

    public void setAlcunhas(List<AlcunhaModel> alcunhas) {
        this.alcunhas = alcunhas;
    }

    public void setAkumanomi(AkumanomiModel akumanomi) {
        this.akumanomi = akumanomi;
    }
}
