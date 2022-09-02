package com.algaworks.example.mensagem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MensagemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MensagemApplication.class, args);
	}

}
