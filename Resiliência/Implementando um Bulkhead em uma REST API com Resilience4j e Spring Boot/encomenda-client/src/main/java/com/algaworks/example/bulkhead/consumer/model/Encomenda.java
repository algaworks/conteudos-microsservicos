package com.algaworks.example.bulkhead.consumer.model;

public class Encomenda {

	private Long id;
	private Status status = Status.PENDENTE;
	private String endereco;
	private String complemento;

	public Encomenda() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void marcarComoEmAndamento() {
		this.status = Status.EM_ANDAMENTO;
	}

	public void marcarComoEntregue() {
		this.status = Status.ENTREGUE;
	}

	public enum Status {
		PENDENTE,EM_ANDAMENTO,ENTREGUE;
	}
}
