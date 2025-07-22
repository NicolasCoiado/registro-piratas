package br.com.nvnk.RegistroPiratas.Akumanomis;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AkumanomiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TiposAkumanomi tipo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "img_url")
    private String img_url;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private BigInteger id_usuario;

}
