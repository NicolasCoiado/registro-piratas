package br.com.nvnk.RegistroPiratas.Tripulacoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripulacaoRepository extends JpaRepository<TripulacaoModel, Long> {
}
