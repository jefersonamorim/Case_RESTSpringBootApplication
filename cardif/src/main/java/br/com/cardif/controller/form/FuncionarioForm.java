package br.com.cardif.controller.form;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;

import br.com.cardif.model.Cargo;
import br.com.cardif.model.Departamento;
import br.com.cardif.model.Funcionario;
import br.com.cardif.repository.CargoRepository;
import br.com.cardif.repository.DepartamentoRepository;
import br.com.cardif.repository.FuncionarioRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class FuncionarioForm {
	@ApiModelProperty(notes= "Nome do Funcionário.")
	@NotNull()
	@NotEmpty
	private String name;

	@NotNull
	@Min(1)
	@Max(110)
	@ApiModelProperty(notes="Idade deve ser entre 01 e 110.")
	private Integer age;

	@NotNull
	@ApiModelProperty(required = true)
	private LocalDate birthday;

	@NotNull
	@NotEmpty
	@ApiModelProperty(notes="Identificador único.", required = true, example = "47.405.317-9")
	private String document;
	
	@NotNull
	@ApiModelProperty(notes="Identificador do cargo.")
	private Integer idCargo;
	
	@NotNull
	@ApiModelProperty(notes="Identificador departamento.")
	private Integer idDepartamento;
	
	private List<String> historicoDepartamento;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Integer getIdCargo() {
		return idCargo;
	}

	public Integer getIdDepartamento() {
		return idDepartamento;
	}

	public Funcionario converter(CargoRepository cargoRepository, DepartamentoRepository departamentoRepository) {
		Cargo cargo = cargoRepository.getOne(this.idCargo);
		
		Departamento departamento = departamentoRepository.getOne(idDepartamento);
		
		return new Funcionario(name, age, birthday, document, cargo, departamento, historicoDepartamento);
	}

	public Funcionario atualizar(Integer id, FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, DepartamentoRepository departamentoRepository) {
		Funcionario funcionarioRecuperado = funcionarioRepository.getOne(id);

		if (Strings.isNotEmpty(this.name)) {
			funcionarioRecuperado.setName(this.name);
		}
		if (this.age != null) {
			funcionarioRecuperado.setAge(this.age);
		}
		if (this.birthday != null) {
			funcionarioRecuperado.setBirthday(this.birthday);
		}
		if (Strings.isNotEmpty(this.document)) {
			funcionarioRecuperado.setDocument(this.document);
		}
		
		if (this.idCargo != null) {
			funcionarioRecuperado.setCargo(cargoRepository.getOne(this.idCargo));
		}
		
		if (this.idDepartamento != null ) {
			funcionarioRecuperado.setDepartamento(departamentoRepository.getOne(this.idDepartamento));
		}
		if (historicoDepartamento != null) {
			String historicoDepartamentoText = "";
			for (String d : this.historicoDepartamento) {
				historicoDepartamentoText += d +",";
			}
			funcionarioRecuperado.setHistoricoDepartamentos(historicoDepartamentoText);
			
		}
		

		return funcionarioRecuperado;
	}

	public Boolean verificaExistencia(FuncionarioForm funcionarioForm, FuncionarioRepository funcionarioRepository) {
		Optional<Funcionario> funcionario = funcionarioRepository.findByDocument(this.document);

		if (funcionario.isPresent()) {
			return true;
		}

		return false;
	}

	public Funcionario promover(Funcionario funcionarioBanco, FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
		Funcionario funcionarioRecuperado = funcionarioRepository.getOne(funcionarioBanco.getId());

			funcionarioRecuperado.setName(funcionarioBanco.getName());
			funcionarioRecuperado.setAge(funcionarioBanco.getAge());
			funcionarioRecuperado.setBirthday(funcionarioBanco.getBirthday());
			funcionarioRecuperado.setDocument(funcionarioBanco.getDocument());
			funcionarioRecuperado.setCargo(cargoRepository.getOne(1));
			funcionarioRecuperado.setDepartamento(funcionarioBanco.getDepartamento());
			
			funcionarioRecuperado.setHistoricoDepartamentos(funcionarioBanco.getHistoricoDepartamentos());
			
		return funcionarioRecuperado;
	}

	public List<String> getHistoricoDepartamento() {
		return historicoDepartamento;
	}

	public void setHistoricoDepartamento(List<String> historicoDepartamento) {
		this.historicoDepartamento = historicoDepartamento;
	}

}
