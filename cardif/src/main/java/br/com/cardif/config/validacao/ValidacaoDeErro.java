package br.com.cardif.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cardif.dto.ErroFormularioDto;

@RestControllerAdvice
public class ValidacaoDeErro {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroFormularioDto> dto = new ArrayList<>();
		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
		
		fieldErros.forEach(f ->{
			//recuperando mensagem de erro
			String mensagem = messageSource.getMessage(f, LocaleContextHolder.getLocale());
			ErroFormularioDto form =  new ErroFormularioDto(f.getField(), mensagem);
			
			dto.add(form);
		});
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	public ErroFormularioDto handleCadastro(IncorrectResultSizeDataAccessException exception) {
		
		ErroFormularioDto dto = new ErroFormularioDto("", exception.getLocalizedMessage());
		
		return dto;
	}
}
