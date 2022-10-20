package com.example.algaworks.user.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserEntityNotFoundException extends RuntimeException {

	public UserEntityNotFoundException() {
		super("Usuário não encontrado.");
	}

	public UserEntityNotFoundException(String message) {
		super(message);
	}
}
