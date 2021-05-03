package br.com.cardif.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cardif.model.HistFuncionarioDepartamento;

public class HistFuncionarioDptoDto {
	
	private String nomeFuncionario;
	
	private String nomeDepartamento;
	
	private LocalDate dataEntrada;
	
	private LocalDate dataSaida;
	
	public HistFuncionarioDptoDto(HistFuncionarioDepartamento histFuncionarioDepartamento) {
		//this.nomeDepartamento = histFuncionarioDepartamento.getDepartamentoId();
		//this.nomeFuncionario = histFuncionarioDepartamento.getId();
		this.dataEntrada = histFuncionarioDepartamento.getDataEntrada();
		this.dataSaida = histFuncionarioDepartamento.getDataSaida();
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public static List<HistFuncionarioDptoDto> converter(List<HistFuncionarioDepartamento> list) {
		return list.stream().map(HistFuncionarioDptoDto::new).collect(Collectors.toList());
	}
	
	
}
