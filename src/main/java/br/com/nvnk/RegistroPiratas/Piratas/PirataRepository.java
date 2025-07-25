package br.com.nvnk.RegistroPiratas.Piratas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PirataRepository extends JpaRepository<PirataModel, Long> {
}
