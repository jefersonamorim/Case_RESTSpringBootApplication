package br.com.cardif.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HistFuncionarioDepartamento {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	private Integer funcionarioId;
	
	private Integer departamentoId;
	
	private LocalDate dataEntrada = LocalDate.now();
	
	private LocalDate dataSaida;

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public HistFuncionarioDepartamento() {
		super();
	}
	public HistFuncionarioDepartamento(Integer funcionarioId, Integer departamentoId) {
		this.setFuncionarioId(funcionarioId);
		this.setDepartamentoId(departamentoId);
	}

	public HistFuncionarioDepartamento(Funcionario funcionarioRecuperado, Departamento departamento, LocalDate now) {
		this.setFuncionarioId(funcionarioRecuperado.getId());
		this.setDepartamentoId(departamento.getId());
		this.dataSaida = now;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Integer funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public Integer getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Integer departamentoId) {
		this.departamentoId = departamentoId;
	}


	
	
	
	
}
