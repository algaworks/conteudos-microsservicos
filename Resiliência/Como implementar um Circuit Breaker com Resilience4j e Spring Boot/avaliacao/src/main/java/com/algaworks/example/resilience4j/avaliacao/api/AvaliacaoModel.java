package com.algaworks.example.resilience4j.avaliacao.api;

import com.algaworks.example.resilience4j.avaliacao.domain.Avaliacao;

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
	
	public static AvaliacaoModel of(Avaliacao avaliacao) {
		return new AvaliacaoModel(
				avaliacao.getId(), 
				avaliacao.getNota(),
				avaliacao.getDescricao(),
				avaliacao.getNomeAvaliador());
	}
}
