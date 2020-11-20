package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.model.AcaoModel;

public interface AcaoRepository extends JpaRepository<AcaoModel, Long> {

}
