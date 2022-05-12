package com.algaworks.example.mensagem.integration;

import com.algaworks.example.mensagem.api.PaisResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocalidadesClient {
	
	private final Logger logger = LoggerFactory.getLogger(LocalidadesClient.class);
	private final String paisAPIUrl;
	private final String localidadesAPIPaisPath;
	private final RestTemplate restTemplate;

	public LocalidadesClient(@Value("${integracao.api.localidades.url}") String localidadesAPIUrl,
	                         @Value("${integracao.api.localidades.paises-path}") String localidadesAPIPaisPath,
	                         RestTemplate restTemplate) {
		this.paisAPIUrl = localidadesAPIUrl;
		this.localidadesAPIPaisPath = localidadesAPIPaisPath;
		this.restTemplate = restTemplate;
	}

	public List<PaisResponse> getPaises() {
		final var url = paisAPIUrl + localidadesAPIPaisPath;
		final var resposta = restTemplate.getForObject(url, LocalidadesResponse[].class);
		
		if (resposta == null) {
			return new ArrayList<>();
		}
		
		return Arrays.stream(resposta)
				.map(PaisResponse::deLocalidadesResponse)
				.collect(Collectors.toList());
	}

	public boolean estaOnline() {
		try {
			ResponseEntity<String> resposta = restTemplate.getForEntity(paisAPIUrl, String.class);
			return resposta.getStatusCode().is2xxSuccessful();
		} catch (Exception e){
			logger.error("Integração com API de localidades está offline");
			return false;
		}
	}
	
}
