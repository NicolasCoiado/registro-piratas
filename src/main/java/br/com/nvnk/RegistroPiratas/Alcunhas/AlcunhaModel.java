package br.com.nvnk.RegistroPiratas.Alcunhas;

import br.com.nvnk.RegistroPiratas.Piratas.PirataModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlcunhaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "titulo")
    private String titulo;

    @ManyToMany(mappedBy = "alcunhas")
    private PirataModel pirata;
}
