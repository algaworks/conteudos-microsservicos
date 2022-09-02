package com.algaworks.example.ratelimiter.mensagem.client;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RateLimiterTest {

    @Test
    public void deveLancarRequestNotPermitted__quandoOLimiteForUltrapassado() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(4)
                .limitRefreshPeriod(Duration.ofSeconds(6))
                .timeoutDuration(Duration.ofSeconds(1))
                .build();

        RateLimiter rateLimiter = RateLimiter.of("MensagemRateLimiter", config);

        for (int i = 0; i < 4; i++) {
            rateLimiter.executeRunnable(()-> System.out.println("Olá mundo"));
        }

        Assertions.assertThatExceptionOfType(RequestNotPermitted.class)
                .isThrownBy(()-> rateLimiter.executeRunnable(()-> System.out.println("Olá mundo")));
    }

    @Test
    public void devePermitirUmaNovaChamadaCasoTimeoutNaoSejaUltrapassado() throws InterruptedException {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(4)
                .limitRefreshPeriod(Duration.ofSeconds(6))
                .timeoutDuration(Duration.ofSeconds(1))
                .build();

        RateLimiter rateLimiter = RateLimiter.of("MensagemRateLimiter", config);

        for (int i = 0; i < 4; i++) {
            rateLimiter.executeRunnable(()-> System.out.println("Olá mundo"));
        }

        Thread.sleep(Duration.ofSeconds(5).toMillis());
        rateLimiter.executeRunnable(()-> System.out.println("Olá mundo"));
    }

    @Test
    public void instanciasDevemSerIguaisCasoSejamCriadasPeloRegistry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(4)
                .limitRefreshPeriod(Duration.ofSeconds(6))
                .timeoutDuration(Duration.ofSeconds(1))
                .build();

        RateLimiterRegistry registry = RateLimiterRegistry.of(config);

        RateLimiter rateLimiter1 = registry.rateLimiter("MensagemRateLimiter");
        RateLimiter rateLimiter2 = registry.rateLimiter("MensagemRateLimiter");

        Assertions.assertThat(rateLimiter1).isEqualTo(rateLimiter2);
    }

}
