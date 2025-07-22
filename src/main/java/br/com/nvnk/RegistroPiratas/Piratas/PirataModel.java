package br.com.nvnk.RegistroPiratas.Piratas;

import br.com.nvnk.RegistroPiratas.Akumanomis.AkumanomiModel;
import br.com.nvnk.RegistroPiratas.Alcunhas.AlcunhaModel;
import br.com.nvnk.RegistroPiratas.Tripulacoes.TripulacoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PirataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "recompensa")
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
    private List<AlcunhaModel> alcunhas;

    @OneToOne
    @JoinColumn(name = "id_akumanomi")
    private AkumanomiModel akumanomi;
}
