package com.algaworks.example.mensagem.api;

import com.algaworks.example.mensagem.integration.LocalidadesClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisController {
	
	private final LocalidadesClient localidadesClient;

	public PaisController(LocalidadesClient localidadesClient) {
		this.localidadesClient = localidadesClient;
	}

	@GetMapping
	public List<PaisResponse> buscarPaises(){
		return localidadesClient.getPaises();
	}
	
}
