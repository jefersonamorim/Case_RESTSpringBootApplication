package br.com.cardif.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cardif.controller.dto.FuncionarioDto;
import br.com.cardif.controller.dto.PersistenceDto;
import br.com.cardif.controller.form.FuncionarioForm;
import br.com.cardif.model.Funcionario;
import br.com.cardif.model.HistFuncionarioDepartamento;
import br.com.cardif.repository.CargoRepository;
import br.com.cardif.repository.DepartamentoRepository;
import br.com.cardif.repository.FuncionarioRepository;
import br.com.cardif.repository.HistFuncionarioDptoRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired 
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private HistFuncionarioDptoRepository histFuncDptoRepository;
	
	@PostMapping
	@Transactional
	private ResponseEntity<?> cadastrar(@RequestBody @Valid FuncionarioForm funcionarioForm, UriComponentsBuilder uriComponentsBuilder){
		Boolean existe = funcionarioForm.verificaExistencia(funcionarioForm, this.funcionarioRepository);
		
		if (existe) {
			return ResponseEntity.ok().body(new PersistenceDto("Valor no campo 'Document' já existente."));
		}
		
		Funcionario funcionario = funcionarioForm.converter(this.cargoRepository, this.departamentoRepository, this.histFuncDptoRepository);
		funcionarioRepository.save(funcionario);
		
		HistFuncionarioDepartamento hist = new HistFuncionarioDepartamento(funcionario.getId(), funcionario.getDepartamento().getId());
		
		histFuncDptoRepository.save(hist);
		
		URI uri = uriComponentsBuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();
		return ResponseEntity.created(uri).body(new FuncionarioDto(funcionario));
	}
	
	@GetMapping
	private List<FuncionarioDto> listar(String funcionarioName, String departamentoName){
		if (funcionarioName == null && departamentoName == null) {
			List<Funcionario> funcionarios = funcionarioRepository.findAll();
			return FuncionarioDto.converter(funcionarios);
		}else if(!Strings.isEmpty(funcionarioName)) {
			List<Funcionario> funcionarios = funcionarioRepository.findByName(funcionarioName);
			return FuncionarioDto.converter(funcionarios);
		}else if(!Strings.isEmpty(departamentoName)) {
			List<Funcionario> funcionarios = funcionarioRepository.findByDepartamento_name(departamentoName);
			
			return FuncionarioDto.converter(funcionarios);
		}else {
			return new ArrayList<FuncionarioDto>();
		}
		
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<FuncionarioDto> findId(@PathVariable Integer id){
		Optional<Funcionario> funcionarioBanco = this.funcionarioRepository.findById(id);
		
		if (funcionarioBanco.isPresent()) {
			return ResponseEntity.ok(new FuncionarioDto(funcionarioBanco.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	private ResponseEntity<FuncionarioDto> atualizar(@PathVariable Integer id, @RequestBody @Valid FuncionarioForm form) {
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		
		if (funcionario.isPresent()) {
			Funcionario funcionarioAtualziado = form.atualizar(funcionario.get().getId(), this.funcionarioRepository, this.cargoRepository, this.departamentoRepository, this.histFuncDptoRepository);
			this.funcionarioRepository.save(funcionarioAtualziado);
			
			return ResponseEntity.ok(new FuncionarioDto(funcionarioAtualziado));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	private ResponseEntity<?> deletar(@PathVariable Integer id) {
		
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		if (funcionario.isPresent()) {
			this.funcionarioRepository.deleteById(funcionario.get().getId());
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	

	@GetMapping("/departamento/{id}")
	private List<FuncionarioDto> buscarFuncionarioPorDepartamentoId(@PathVariable @NotNull Integer id){
		
		List<Funcionario> funcionarios = funcionarioRepository.findByDepartamento_Id(id);
		
		return FuncionarioDto.converter(funcionarios);
		
	}
	
	@GetMapping("/departamento")
	private List<FuncionarioDto> buscarFuncionarioDepartamentoName(String nameDepartamento){
		if (nameDepartamento != null && !Strings.isEmpty(nameDepartamento)) {
			List<Funcionario> funcionarios = funcionarioRepository.findByDepartamento_Name(nameDepartamento);
			
			return FuncionarioDto.converter(funcionarios);
		}
		return new ArrayList<FuncionarioDto>();
	}
	
	@PutMapping("/promover")
	@ResponseBody()
	private ResponseEntity<FuncionarioDto> promoverFuncionario(Integer idFuncionario, String documentFuncionario){
		
		if (idFuncionario != null && idFuncionario > 0) {
			Funcionario funcionarioBanco = funcionarioRepository.getOne(idFuncionario);
			if (Objects.nonNull(funcionarioBanco)) {
			
				FuncionarioForm form = new FuncionarioForm();
				Funcionario funcionarioAtualziado = form.promover(funcionarioBanco, this.funcionarioRepository, this.cargoRepository);
				this.funcionarioRepository.save(funcionarioAtualziado);
				
				return ResponseEntity.ok(new FuncionarioDto(funcionarioAtualziado));
				
			}
		}
		else if (documentFuncionario != null && Strings.isNotEmpty(documentFuncionario)) {
			Optional<Funcionario> funcionarioBanco = funcionarioRepository.findByDocument(documentFuncionario);
			
			if (funcionarioBanco.isPresent()) {
				
				FuncionarioForm form = new FuncionarioForm();
				Funcionario funcionarioAtualziado = form.promover(funcionarioBanco.get(), this.funcionarioRepository, this.cargoRepository);
				this.funcionarioRepository.save(funcionarioAtualziado);
				
				return ResponseEntity.ok(new FuncionarioDto(funcionarioAtualziado));
			}
			
		} 
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/historicoFuncionarioDepto")
	private List<HistFuncionarioDepartamento> listarHistFuncionarioDepto(Integer idDepartamento, Integer idFuncionario) {
		if (idDepartamento != null && idDepartamento > 0) {
			Optional<List<HistFuncionarioDepartamento>> histByDepto = histFuncDptoRepository.findBydepartamentoId(idDepartamento);
			
			if (histByDepto.isPresent()) {
				return histByDepto.get();
			}
		}else if(idFuncionario != null && idFuncionario > 0) {
			Optional<List<HistFuncionarioDepartamento>> histByNameDpto = histFuncDptoRepository.findByfuncionarioId(idFuncionario);
			
			if (histByNameDpto.isPresent()) {
				return histByNameDpto.get();
			}
		}else {
			
			return histFuncDptoRepository.findAll();
		}
		
		return new ArrayList<HistFuncionarioDepartamento>();
	}
		
	
}
