package com.algaworks.example.ratelimiter.mensagem.client.client;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MensagemClientIT {

    @Autowired
    MensagemClient mensagemClient;

    @Test
    void deveLancarUmaExceptionCasoOLimiteSejaAntigido() {
        for (int i = 1; i <= 17; i++) {
            mensagemClient.listar(0,15);
        }

        Assertions.assertThatExceptionOfType(RequestNotPermitted.class)
                .isThrownBy(()-> mensagemClient.listar(0,15));
    }
}