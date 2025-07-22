package br.com.nvnk.RegistroPiratas.Akumanomis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AkumanomiRepository extends JpaRepository <AkumanomiModel, Long> {
}
