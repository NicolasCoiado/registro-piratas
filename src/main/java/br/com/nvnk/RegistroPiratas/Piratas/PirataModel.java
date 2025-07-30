package br.com.nvnk.RegistroPiratas.Piratas;

import br.com.nvnk.RegistroPiratas.Akumanomis.AkumanomiModel;
import br.com.nvnk.RegistroPiratas.Tripulacoes.TripulacaoModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "piratas")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PirataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "recompensa", precision = 12, scale = 2)
    private BigDecimal recompensa;

    @Column(name = "img_url")
    private String img_url;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "alcunhas")
    private String alcunhas;

    @Column(name = "vivo_morto")
    private Boolean vivo_morto;

    @Column(name = "capitao")
    private Boolean capitao;

    @ManyToOne
    @JoinColumn(name = "id_tripulacao")
    private TripulacaoModel tripulacao;

    @OneToOne
    @JoinColumn(name = "id_akumanomi")
    private AkumanomiModel akumanomi;

    public PirataModel() {
    }

    public PirataModel(Long id, String nome, BigDecimal recompensa, String img_url, String descricao, String alcunhas, Boolean vivo_morto, Boolean capitao, TripulacaoModel tripulacao, AkumanomiModel akumanomi) {
        this.id = id;
        this.nome = nome;
        this.recompensa = recompensa;
        this.img_url = img_url;
        this.descricao = descricao;
        this.alcunhas = alcunhas;
        this.vivo_morto = vivo_morto;
        this.capitao = capitao;
        this.tripulacao = tripulacao;
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

    public String getImg_url() {
        return img_url;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getAlcunhas() {
        return alcunhas;
    }

    public Boolean getVivo_morto() {
        return vivo_morto;
    }

    public TripulacaoModel getTripulacao() {
        return tripulacao;
    }

    public AkumanomiModel getAkumanomi() {
        return akumanomi;
    }

    public Boolean getCapitao() { return capitao;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRecompensa(BigDecimal recompensa) {
        this.recompensa = recompensa;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAlcunhas(String alcunhas) {
        this.alcunhas = alcunhas;
    }

    public void setVivo_morto(Boolean vivo_morto) {
        this.vivo_morto = vivo_morto;
    }

    public void setTripulacao(TripulacaoModel tripulacao) {
        this.tripulacao = tripulacao;
    }

    public void setAkumanomi(AkumanomiModel akumanomi) {
        this.akumanomi = akumanomi;
    }

    public void setCapitao(Boolean capitao) { this.capitao = capitao; }
}
