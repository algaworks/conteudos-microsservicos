package com.algaworks.example.bulkhead.producer.infra;

import com.algaworks.example.bulkhead.producer.domain.Encomenda;

public interface NotificadorEncomenda {
   void notificar(Encomenda encomenda);
}