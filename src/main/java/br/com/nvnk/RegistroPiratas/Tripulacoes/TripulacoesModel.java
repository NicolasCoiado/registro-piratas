package br.com.nvnk.RegistroPiratas.Tripulacoes;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripulacoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "titulo")
    private String titulo;

    @OneToOne
    @JoinColumn(name = "capitao_id")
    private PirataModel capitao;

    @OneToMany(mappedBy = "tripulacao")
    private List<PirataModel> membros;
}
