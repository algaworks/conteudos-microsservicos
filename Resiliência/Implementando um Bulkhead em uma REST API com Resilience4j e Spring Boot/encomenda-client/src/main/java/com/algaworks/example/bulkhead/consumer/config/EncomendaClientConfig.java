package com.algaworks.example.bulkhead.consumer.config;

import com.algaworks.example.bulkhead.consumer.client.EncomendaClient;
import com.algaworks.example.bulkhead.consumer.infra.EncomendaClientRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EncomendaClientConfig {

    @Bean
    public EncomendaClient mensagemClient(RestTemplate restTemplate) {
        return new EncomendaClientRestTemplate(restTemplate);
    }

}
