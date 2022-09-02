package com.algaworks.example.ratelimiter.mensagem.client.config;

import com.algaworks.example.ratelimiter.mensagem.client.client.MensagemClient;
import com.algaworks.example.ratelimiter.mensagem.client.infra.MensagemClientRateLimiter;
import com.algaworks.example.ratelimiter.mensagem.client.infra.MensagemClientRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MensagemClientConfig {

    @Bean
    public MensagemClient mensagemClient(RestTemplate restTemplate) {
        return new MensagemClientRestTemplate(restTemplate);
    }

}
