package br.com.cardif.dto;

public class ErroFormularioDto {
	
	private String campo;
	
	private String erro;

	public ErroFormularioDto(String field, String mensagem) {
		this.campo = field;
		this.erro = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
