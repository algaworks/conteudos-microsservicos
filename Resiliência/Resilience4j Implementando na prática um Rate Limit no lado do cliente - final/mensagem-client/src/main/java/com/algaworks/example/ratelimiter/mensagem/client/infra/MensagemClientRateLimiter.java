package com.algaworks.example.ratelimiter.mensagem.client.infra;

import com.algaworks.example.ratelimiter.mensagem.client.client.MensagemClient;
import com.algaworks.example.ratelimiter.mensagem.client.model.Mensagem;
import com.algaworks.example.ratelimiter.mensagem.client.model.MensagemInput;
import com.algaworks.example.ratelimiter.mensagem.client.model.MensagemPage;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

import java.time.Duration;
import java.util.Optional;

public class MensagemClientRateLimiter implements MensagemClient {

    private final MensagemClientRestTemplate clientRestTemplate;
    private final RateLimiter rateLimiter;

    public MensagemClientRateLimiter(MensagemClientRestTemplate clientRestTemplate) {
        this.clientRestTemplate = clientRestTemplate;

        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(17)
                .limitRefreshPeriod(Duration.ofSeconds(3))
                .timeoutDuration(Duration.ofMillis(10))
                .build();

        this.rateLimiter = RateLimiter.of("MensagemRateLimiter", config);
    }

    @Override
    public Mensagem criar(MensagemInput input) {
        return rateLimiter.executeSupplier(()-> clientRestTemplate.criar(input));
    }

    @Override
    public Optional<Mensagem> buscarUm(Long id) {
        return rateLimiter.executeSupplier(()-> clientRestTemplate.buscarUm(id));
    }

    @Override
    public MensagemPage listar(int pagina, int tamanho) {
        return rateLimiter.executeSupplier(()-> clientRestTemplate.listar(pagina, tamanho));
    }
}
