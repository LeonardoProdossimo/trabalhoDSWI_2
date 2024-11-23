package org.libertas;

public class Retorno {
	private Boolean sucesso;
	private String mensagem;
	
	public Retorno() {
		this.sucesso = true;
		this.mensagem = null;
	}

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
