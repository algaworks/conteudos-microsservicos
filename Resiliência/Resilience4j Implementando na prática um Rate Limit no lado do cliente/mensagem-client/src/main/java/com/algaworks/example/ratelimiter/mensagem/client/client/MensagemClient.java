package com.algaworks.example.ratelimiter.mensagem.client.client;

import com.algaworks.example.ratelimiter.mensagem.client.model.Mensagem;
import com.algaworks.example.ratelimiter.mensagem.client.model.MensagemInput;
import com.algaworks.example.ratelimiter.mensagem.client.model.MensagemPage;

import java.util.Optional;

public interface MensagemClient {
    Mensagem criar(MensagemInput input);
    Optional<Mensagem> buscarUm(Long id);
    MensagemPage listar(int pagina, int tamanho);
}
