package com.algaworks.example.bulkhead.consumer.infra;

import com.algaworks.example.bulkhead.consumer.client.EncomendaClient;
import com.algaworks.example.bulkhead.consumer.model.Encomenda;
import com.algaworks.example.bulkhead.consumer.model.EncomendaPage;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class EncomendaClientRestTemplate implements EncomendaClient {
    public static final String API_URL = "http://localhost:8080/encomendas";

    private final RestTemplate restTemplate;

    public EncomendaClientRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Encomenda criar(Encomenda encomenda) {
        return restTemplate.postForObject(API_URL, encomenda, Encomenda.class);
    }

    @Override
    public Optional<Encomenda> buscarUm(Long id) {
        return Optional.ofNullable(restTemplate.getForObject(API_URL + "/" + id, Encomenda.class));
    }

    @Override
    public EncomendaPage listar(int pagina, int tamanho) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", pagina);
        params.put("size", tamanho);
        var mensagens = restTemplate.getForObject(API_URL, EncomendaPage.class, params);
        return Optional.ofNullable(mensagens).orElse(new EncomendaPage());
    }

    @Override
    public void marcarComoEmAndamento(Long encomendaId) {
        restTemplate.postForObject(API_URL + "/" + encomendaId + "/marcar-como-em-andamento", null, Void.class);
    }

    @Override
    public void marcarComoEntregue(Long encomendaId) {
        restTemplate.postForObject(API_URL + "/" + encomendaId + "/marcar-como-entregue", null, Void.class);
    }

}
