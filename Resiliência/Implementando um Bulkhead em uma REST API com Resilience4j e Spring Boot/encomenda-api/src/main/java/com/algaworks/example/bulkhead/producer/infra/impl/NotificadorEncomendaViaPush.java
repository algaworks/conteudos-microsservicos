package com.algaworks.example.bulkhead.producer.infra.impl;

import com.algaworks.example.bulkhead.producer.domain.Encomenda;
import com.algaworks.example.bulkhead.producer.infra.NotificadorEncomenda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class NotificadorEncomendaViaPush implements NotificadorEncomenda {

    private final Logger log = LoggerFactory.getLogger(NotificadorEncomendaViaPush.class);

    @Override
    public void notificar(Encomenda encomenda) {
        log.info("Notificação via push iniciada");
        try {
            Thread.sleep(Duration.ofMinutes(1).toMillis());
        } catch (InterruptedException e) {
        }
        log.info("Notificação via push concluída");
    }

}
