package com.algaworks.example.mensagem.api;

import com.algaworks.example.mensagem.domain.Mensagem;

import java.time.OffsetDateTime;

public class MensagemResponse {

	private Long id;
	private UsuarioResponse autor;
	private OffsetDateTime dataHoraCriacao;
	private String conteudo;

	public MensagemResponse() {
		
	}

	public MensagemResponse(Long id,
	                        UsuarioResponse autor, 
	                        OffsetDateTime dataHoraCriacao, 
	                        String conteudo) {
		this.id = id;
		this.autor = autor;
		this.dataHoraCriacao = dataHoraCriacao;
		this.conteudo = conteudo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioResponse getAutor() {
		return autor;
	}

	public void setAutor(UsuarioResponse autor) {
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
	
	public static MensagemResponse daMensagem(Mensagem mensagem){
		return new MensagemResponse(
				mensagem.getId(),
				UsuarioResponse.doUsuario(mensagem.getAutor()),
				mensagem.getDataHoraCriacao(),
				mensagem.getConteudo()
		);
	}
}
