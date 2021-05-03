package br.com.cardif.controller.dto;

public class PersistenceDto {
	
	private String mensagem;

	
	
	public PersistenceDto(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
