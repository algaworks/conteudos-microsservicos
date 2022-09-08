package com.algaworks.example.bulkhead.consumer.client;

import com.algaworks.example.bulkhead.consumer.model.Encomenda;
import com.algaworks.example.bulkhead.consumer.model.EncomendaPage;

import java.util.Optional;

public interface EncomendaClient {
    Encomenda criar(Encomenda encomenda);
    Optional<Encomenda> buscarUm(Long encomendaId);
    EncomendaPage listar(int pagina, int tamanho);
    void marcarComoEmAndamento(Long encomendaId);
    void marcarComoEntregue(Long encomendaId);
}
