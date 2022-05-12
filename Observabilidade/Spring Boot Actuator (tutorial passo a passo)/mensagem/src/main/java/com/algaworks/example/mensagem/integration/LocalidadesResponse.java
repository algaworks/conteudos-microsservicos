package com.algaworks.example.mensagem.integration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalidadesResponse {
	
	private PaisId id;
	private String nome;

	public PaisId getId() {
		return id;
	}

	public void setId(PaisId id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static class PaisId {
		
		@JsonProperty("M49")
		private Long m49;
		
		@JsonProperty("ISO-ALPHA-2")
		private String isoAlpha2;
		
		@JsonProperty("ISO-ALPHA-3")
		private String isoAlpha3;

		public Long getM49() {
			return m49;
		}

		public void setM49(Long m49) {
			this.m49 = m49;
		}

		public String getIsoAlpha2() {
			return isoAlpha2;
		}

		public void setIsoAlpha2(String isoAlpha2) {
			this.isoAlpha2 = isoAlpha2;
		}

		public String getIsoAlpha3() {
			return isoAlpha3;
		}

		public void setIsoAlpha3(String isoAlpha3) {
			this.isoAlpha3 = isoAlpha3;
		}
	}
}
