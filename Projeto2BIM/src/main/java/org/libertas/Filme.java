package org.libertas;

public class Filme {
	private Integer id, duracao;
	private String titulo, genero, diretor, distribuidor;
	
	public Filme() {
		id = 0;
		titulo = "";
		genero = "";
		duracao = 0; 
		diretor = ""; 
		distribuidor = ""; 
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGernero(String genero) {
		this.genero = genero;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}
}
