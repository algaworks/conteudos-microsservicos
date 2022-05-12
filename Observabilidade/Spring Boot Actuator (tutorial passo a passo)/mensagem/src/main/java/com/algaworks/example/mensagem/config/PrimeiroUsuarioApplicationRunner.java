package com.algaworks.example.mensagem.config;

import com.algaworks.example.mensagem.domain.Usuario;
import com.algaworks.example.mensagem.domain.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PrimeiroUsuarioApplicationRunner implements ApplicationRunner {
	
	private final Logger logger = LoggerFactory.getLogger(PrimeiroUsuarioApplicationRunner.class);
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public PrimeiroUsuarioApplicationRunner(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (usuarioRepository.count() != 0) {
			return;
		}
		logger.info("Nenhum usuário encontrado, cadastrando usuários padrão.");
		
		usuarioRepository.save(
				new Usuario(
						"Administrador",
						"admin@email.com",
						passwordEncoder.encode("admin"),
						"Brasil",
						Usuario.Permissao.ADMIN
				)
		);

		usuarioRepository.save(
				new Usuario(
						"Cliente",
						"cliente@email.com",
						passwordEncoder.encode("123"),
						"Brasil",
						Usuario.Permissao.CLIENTE
				)
		);
	}
}
