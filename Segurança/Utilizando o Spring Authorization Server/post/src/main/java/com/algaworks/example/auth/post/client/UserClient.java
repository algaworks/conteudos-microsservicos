package com.algaworks.example.auth.post.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class UserClient {
	
	private final RestTemplate restTemplate;
	private final UserClientProperties properties;
	private final Logger logger = LoggerFactory.getLogger(UserClient.class);

	public UserClient(RestTemplate restTemplate, UserClientProperties properties) {
		this.restTemplate = restTemplate;
		this.properties = properties;
	}

	public Optional<UserResponse> findById(Long id) {
		try {
			final String url = properties.getUrl() + "/users/{id}";
			final UserResponse response = restTemplate.getForObject(url, UserResponse.class, id);
			return Optional.ofNullable(response);
		} catch (Exception e) {
			logger.error(String.format("Erro ao buscar usuário de id %s", id), e);
			return Optional.empty();
		}
	}
	
}
