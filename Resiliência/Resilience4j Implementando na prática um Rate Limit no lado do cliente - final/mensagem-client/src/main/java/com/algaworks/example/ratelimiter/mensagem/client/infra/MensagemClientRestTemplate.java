package com.algaworks.example.ratelimiter.mensagem.client.infra;

import com.algaworks.example.ratelimiter.mensagem.client.client.MensagemClient;
import com.algaworks.example.ratelimiter.mensagem.client.model.Mensagem;
import com.algaworks.example.ratelimiter.mensagem.client.model.MensagemInput;
import com.algaworks.example.ratelimiter.mensagem.client.model.MensagemPage;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class MensagemClientRestTemplate implements MensagemClient {
    public static final String API_URL = "http://localhost:8080/mensagens";

    private final RestTemplate restTemplate;

    public MensagemClientRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @RateLimiter(name = "MessagemRateLimiter")
    public Mensagem criar(MensagemInput input) {
        return restTemplate.postForObject(API_URL, input, Mensagem.class);
    }

    @Override
    @RateLimiter(name = "MessagemRateLimiter")
    public Optional<Mensagem> buscarUm(Long id) {
        return Optional.ofNullable(restTemplate.getForObject(API_URL + "/" + id, Mensagem.class));
    }

    @Override
    @RateLimiter(name = "MessagemRateLimiter")
    public MensagemPage listar(int pagina, int tamanho) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", pagina);
        params.put("size", tamanho);
        var mensagens = restTemplate.getForObject(API_URL, MensagemPage.class, params);
        return Optional.ofNullable(mensagens).orElse(new MensagemPage());
    }

}
