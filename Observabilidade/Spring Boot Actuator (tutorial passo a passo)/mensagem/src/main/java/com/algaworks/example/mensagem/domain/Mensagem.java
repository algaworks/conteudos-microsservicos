package com.algaworks.example.mensagem.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;

@Entity
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreatedBy
	@ManyToOne
	private Usuario autor;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataHoraCriacao;
	
	private String conteudo;

	public Mensagem() {
		
	}

	public Mensagem(Usuario autor, String conteudo) {
		this.autor = autor;
		this.conteudo = conteudo;
	}

	public Mensagem(Long id, Usuario autor, String conteudo) {
		this.id = id;
		this.autor = autor;
		this.conteudo = conteudo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public OffsetDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(OffsetDateTime dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}
}
