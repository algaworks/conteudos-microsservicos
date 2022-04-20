package com.algaworks.example.mensagem.api;

import com.algaworks.example.mensagem.domain.Usuario;

import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

	@NotBlank
	private String nome;

	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	@NotBlank
	private String pais;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public Usuario converterParaUsuario(){
		return new Usuario(
				this.getNome(),
				this.getEmail(),
				this.getSenha(),
				this.getPais(),
				null
		);
	}
}
