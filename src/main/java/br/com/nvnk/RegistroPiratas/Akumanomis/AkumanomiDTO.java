package br.com.nvnk.RegistroPiratas.Akumanomis;

public class AkumanomiDTO {
    private Long id;
    private String nome;
    private TiposAkumanomi tipo;
    private String descricao;
    private String img_url;
    private Long id_usuario;

    public AkumanomiDTO() {
    }

    public AkumanomiDTO(Long id, String nome, TiposAkumanomi tipo, String descricao, String img_url, Long id_usuario) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.img_url = img_url;
        this.id_usuario = id_usuario;
    }

    public Long getId() { return id;}

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

    public Long getId_usuario() {
        return id_usuario;
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

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
    @Override
    public String toString() {
        return "AkumanomiDTO:"+
                "\n Id: "+id+
                "\n Nome: "+nome+
                "\n Tipo: "+tipo+
                "\n Descrição: "+descricao+
                "\n Imagem: "+img_url+
                "\n Id usuario: "+ id_usuario;
    }
}
