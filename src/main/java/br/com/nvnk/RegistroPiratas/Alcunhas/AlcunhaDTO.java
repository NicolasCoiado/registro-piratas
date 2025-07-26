package br.com.nvnk.RegistroPiratas.Alcunhas;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;

import java.util.ArrayList;
import java.util.List;

public class AlcunhaDTO {
    private Long id;
    private String titulo;
    private List<PirataModel> piratas = new ArrayList<>();

    public AlcunhaDTO() {
    }

    public AlcunhaDTO(Long id, String titulo, List<PirataModel> piratas) {
        this.id = id;
        this.titulo = titulo;
        this.piratas = piratas;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<PirataModel> getPiratas() {
        return piratas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPiratas(List<PirataModel> piratas) {
        this.piratas = piratas;
    }
}
