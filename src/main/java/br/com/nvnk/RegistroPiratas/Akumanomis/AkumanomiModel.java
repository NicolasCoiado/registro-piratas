package br.com.nvnk.RegistroPiratas.Akumanomis;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import jakarta.persistence.*;

@Entity
@Table(name = "akumanomis")
public class AkumanomiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TiposAkumanomi tipo;


    @Column(name = "descricao")
    private String descricao;

    @Column(name = "img_url")
    private String img_url;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private PirataModel usuario;

    public AkumanomiModel() {
    }

    public AkumanomiModel(Long id, String nome, TiposAkumanomi tipo, String descricao, String img_url, PirataModel usuario) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.img_url = img_url;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TiposAkumanomi getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImg_url() {
        return img_url;
    }

    public PirataModel getUsuario() {
        return usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(TiposAkumanomi tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setUsuario(PirataModel usuario) {
        this.usuario = usuario;
    }
}
