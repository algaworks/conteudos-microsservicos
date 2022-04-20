package com.algaworks.example.mensagem.api;

import com.algaworks.example.mensagem.domain.Usuario;

public class UsuarioResponse {
	private final Long id;
	private final String nome;
	private final String pais;

	public UsuarioResponse(Long id, String nome, String pais) {
		this.id = id;
		this.nome = nome;
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getPais() {
		return pais;
	}
	
	public static UsuarioResponse doUsuario(Usuario usuario) {
		return new UsuarioResponse(usuario.getId(),
				usuario.getNome(),
				usuario.getPais()
		);
	}
}
