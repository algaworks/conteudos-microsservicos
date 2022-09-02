package com.algaworks.example.mensagem.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SegurancaService {

	public String getUsuario() {
		var context = SecurityContextHolder.getContext();
		return context.getAuthentication().getName();
	}

	public boolean estaAutenticado() {
		if (getUsuario().equals("anonymousUser")) {
			System.out.println("Usuário anônimo");
			return false;
		}
		var context = SecurityContextHolder.getContext();
		boolean authenticated = context.getAuthentication().isAuthenticated();
		if (authenticated) {
			System.out.println("Usuário autenticado");
		}
		return authenticated;
	}

	public boolean naoEstaAutenticado() {
		return !estaAutenticado();
	}

}