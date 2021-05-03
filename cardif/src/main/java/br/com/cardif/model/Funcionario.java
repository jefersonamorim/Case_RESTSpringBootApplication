package br.com.cardif.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Funcionario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private LocalDate birthday;
	
	private String document;
	
	@ManyToOne
	private Cargo cargo;
	
	@ManyToOne()
	@JoinColumn(name="departamento_id")
	private Departamento departamento;
	
	private String historicoDepartamentos;

	public Funcionario() {
		super();
	}
	
	public Funcionario(Integer id, Integer age, LocalDate birthday, String document, Cargo cargo,
			Departamento departamento) {
		super();
		this.id = id;
		this.age = age;
		this.birthday = birthday;
		this.document = document;
		this.cargo = cargo;
		this.departamento = departamento;
	}

	public Funcionario(Funcionario funcionario) {
		this.age = funcionario.age;
		this.birthday = funcionario.birthday;
		this.name = funcionario.name;
		this.document = funcionario.document;
	}
	
	public Funcionario(String name, Integer age, LocalDate birthday, String document) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
		this.document = document;
	}
	
	public Funcionario(String name, Integer age, LocalDate birthday, String document, Cargo cargo, Departamento departamento, List<String> historicoDepartamento) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
		this.document = document;
		this.cargo = cargo;
		this.departamento = departamento;
		
		if (historicoDepartamento != null) {
			String historicoDepartamentoText = "";
			for (String d : historicoDepartamento) {
				historicoDepartamentoText += d + ",";
			}
			this.historicoDepartamentos = historicoDepartamentoText;
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHistoricoDepartamentos() {
		return historicoDepartamentos;
	}

	public void setHistoricoDepartamentos(String historicoDepartamentos) {
		this.historicoDepartamentos = historicoDepartamentos;
	}

	
}
