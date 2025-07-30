package br.com.nvnk.RegistroPiratas.Piratas;

import java.math.BigDecimal;

public class PirataDTO {
    private Long id;
    private String nome;
    private BigDecimal recompensa;
    private String img_url;
    private String descricao;
    private String alcunhas;
    private Boolean vivo_morto;
    private Boolean capitao;
    private Long IdTripulacao;
    private Long IdAkumanomi;


    public PirataDTO() {
    }

    public PirataDTO(Long id, String nome, BigDecimal recompensa, String img_url, String descricao, String alcunhas, Boolean vivo_morto, Boolean capitao, Long idTripulacao, Long idAkumanomi) {
        this.id = id;
        this.nome = nome;
        this.recompensa = recompensa;
        this.img_url = img_url;
        this.descricao = descricao;
        this.alcunhas = alcunhas;
        this.vivo_morto = vivo_morto;
        this.capitao = capitao;
        IdTripulacao = idTripulacao;
        IdAkumanomi = idAkumanomi;
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

    public Boolean getCapitao() {
        return capitao;
    }

    public Long getIdTripulacao() {
        return IdTripulacao;
    }

    public Long getIdAkumanomi() {
        return IdAkumanomi;
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

    public void setCapitao(Boolean capitao) {
        this.capitao = capitao;
    }

    public void setIdTripulacao(Long idTripulacao) {
        IdTripulacao = idTripulacao;
    }

    public void setIdAkumanomi(Long idAkumanomi) {
        IdAkumanomi = idAkumanomi;
    }
}
