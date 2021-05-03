package br.com.cardif.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardif.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

	List<Funcionario> findByName(String nomeFuncionario);

	Optional<Funcionario> findByDocument(String document);

	List<Funcionario> findByDepartamento_Id(Integer id);

	List<Funcionario> findByDepartamento_name(String departamentoName);

	List<Funcionario> findByDepartamento_Name(String nameDepartamento);

}
