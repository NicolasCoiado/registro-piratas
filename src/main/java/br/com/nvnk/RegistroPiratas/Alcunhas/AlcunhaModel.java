package br.com.nvnk.RegistroPiratas.Alcunhas;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alcunhas")
public class AlcunhaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @ManyToMany(mappedBy = "alcunhas")
    private List<PirataModel> piratas = new ArrayList<>();

    public AlcunhaModel() {
    }

    public AlcunhaModel(Long id, String titulo, List<PirataModel> piratas) {
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
