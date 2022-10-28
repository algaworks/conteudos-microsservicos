package com.algaworks.example.s3.core.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	INVALID_DATA("/invalid-data", "Dados inválidos"),
	FORBIDDEN("/forbidden", "Acesso negado"),
	SYSTEM_ERROR("/system-error", "Erro de sistema"),
	INVALID_PARAMETER("/invalid-parameter", "Parâmetro inválido"),
	INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message", "Mensagem incompreensível"),
	RESOURCE_NOT_FOUND("/resource-not-found", "Recurso não encontrado"),
	ENTITY_IN_USE("/entity-in-use", "Entidade em uso"),
	BUSINESS_ERROR("/business-error", "Violação de regra de negócio");
	
	private final String title;
	private final String path;

	ProblemType(String path, String title) {
		this.path = path;
		this.title = title;
	}

}
