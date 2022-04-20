package com.algaworks.example.resilience4j.produtos.client.avaliacoes;

public class AvaliacaoModel {
	
	private Long id;
	private Integer nota;
	private String descricao;
	private String nomeAvaliador;

	public AvaliacaoModel() {
	}

	public AvaliacaoModel(Long id, Integer nota, String descricao, String nomeAvaliador) {
		this.id = id;
		this.nota = nota;
		this.descricao = descricao;
		this.nomeAvaliador = nomeAvaliador;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeAvaliador() {
		return nomeAvaliador;
	}

	public void setNomeAvaliador(String nomeAvaliador) {
		this.nomeAvaliador = nomeAvaliador;
	}
}
