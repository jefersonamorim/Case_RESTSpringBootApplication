package br.com.cardif.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;

import br.com.cardif.model.Funcionario;

public class FuncionarioDto {

	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private LocalDate birthday;
	
	private String document;
	
	private String cargoName;
	
	private String departamentoName;
	
	private List<String> historicoDepartamentos;
	
	public FuncionarioDto(Funcionario funcionario) {
		super();
		this.id   = funcionario.getId();
		this.name = funcionario.getName();
		this.age = funcionario.getAge();
		this.birthday = funcionario.getBirthday();
		this.document = funcionario.getDocument();
		this.cargoName = funcionario.getCargo().getName();
		this.departamentoName = funcionario.getDepartamento().getName();
		
		if (!Strings.isEmpty(funcionario.getHistoricoDepartamentos())) {
			this.historicoDepartamentos = new ArrayList<String>(Arrays.asList(funcionario.getHistoricoDepartamentos().split(",")));
		}else {
			this.historicoDepartamentos = new ArrayList<String>();
		}
		
		
		
	}
	
	public String getCargoName() {
		return cargoName;
	}

	public String getDepartamentoName() {
		return departamentoName;
	}

	public Integer getAge() {
		return age;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getDocument() {
		return document;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public static List<FuncionarioDto> converter(List<Funcionario> funcionarios) {
		
		return funcionarios.stream().map(FuncionarioDto::new).collect(Collectors.toList());
	}

	public List<String> getHistoricoDepartamentos() {
		return historicoDepartamentos;
	}



}
