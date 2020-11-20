package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.model.ExecucaoModel;

public interface ExecucaoRepository extends JpaRepository<ExecucaoModel, Long> {

}
