package br.com.cardif.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardif.model.HistFuncionarioDepartamento;

public interface HistFuncionarioDptoRepository extends JpaRepository<HistFuncionarioDepartamento, Integer>{

	Optional<List<HistFuncionarioDepartamento>> findBydepartamentoId(Integer departamentoId);

	Optional<List<HistFuncionarioDepartamento>> findByfuncionarioId(Integer funcionarioId);

}
