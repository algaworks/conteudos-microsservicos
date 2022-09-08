package com.algaworks.example.bulkhead.consumer.client;

import com.algaworks.example.bulkhead.consumer.infra.EncomendaClientRestTemplate;
import com.algaworks.example.bulkhead.consumer.model.Encomenda;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

class EncomendaClientTest {

    EncomendaClient encomendaClient = new EncomendaClientRestTemplate(new RestTemplate());
    Logger logger = LoggerFactory.getLogger(EncomendaClientTest.class);

    @RepeatedTest(value = 10)
    void criar() {
        CompletableFuture.runAsync(()-> {
            encomendaClient.criar(criarNovaEncomenda());
        });
    }

    @RepeatedTest(value = 10)
    void marcarComoEmAndamento() {
        CompletableFuture.runAsync(()-> {
            encomendaClient.marcarComoEntregue(1L);
        });
    }

    @Test
    void buscarUm() {
        encomendaClient.buscarUm(1L);
    }

    @Test
    void listar() {
        encomendaClient.listar(0,15);
    }

    private static Encomenda criarNovaEncomenda() {
        Encomenda encomenda = new Encomenda();
        encomenda.setEndereco("Avenida Brasil 1006");
        encomenda.setComplemento("apto 1011");
        encomenda.setStatus(Encomenda.Status.PENDENTE);
        return encomenda;
    }
}