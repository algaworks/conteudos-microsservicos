package com.algaworks.example.mensagem.security;

import com.algaworks.example.mensagem.domain.Usuario;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SegurancaService {

	public Optional<Usuario> getUsuario() {
		final var context = SecurityContextHolder.getContext();
		
		if(context == null){
			return Optional.empty();
		}
		
		if(context.getAuthentication() == null){
			return Optional.empty();
		}
		
		final var usuario = (Usuario) context.getAuthentication().getPrincipal();
		return Optional.ofNullable(usuario);
	}

	public Usuario getUsuarioOuFalhe() {
		return this.getUsuario()
				.orElseThrow(()-> new AccessDeniedException("Usuário autenticado não pode ser carregado."));
	}

}