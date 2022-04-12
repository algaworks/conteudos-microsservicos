package com.algaworks.example.mensagem.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SegurancaService {

	public String getUsuario() {
		var context = SecurityContextHolder.getContext();
		return context.getAuthentication().getName();
	}

}