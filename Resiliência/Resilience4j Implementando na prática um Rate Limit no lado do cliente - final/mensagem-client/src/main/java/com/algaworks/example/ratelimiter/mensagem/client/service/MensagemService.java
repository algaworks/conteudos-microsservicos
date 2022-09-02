package com.algaworks.example.ratelimiter.mensagem.client.service;

import com.algaworks.example.ratelimiter.mensagem.client.client.MensagemClient;
import com.algaworks.example.ratelimiter.mensagem.client.model.Mensagem;
import com.algaworks.example.ratelimiter.mensagem.client.model.MensagemInput;
import com.algaworks.example.ratelimiter.mensagem.client.model.MensagemPage;
import org.springframework.stereotype.Service;

@Service
public class MensagemService {

    private final MensagemClient mensagemClient;

    public MensagemService(MensagemClient mensagemClient) {
        this.mensagemClient = mensagemClient;
    }

    public Mensagem criar(MensagemInput mensagem) {
        if (mensagem == null) {
            throw new IllegalArgumentException("Mensagem não pode ser nula.");
        }
        return mensagemClient.criar(mensagem);
    }

    public Mensagem buscarUm(long id) {
        return mensagemClient.buscarUm(id).orElseThrow(MensagemNaoEncontradaException::new);
    }

    public MensagemPage buscarPrimeiraPagina() {
        return mensagemClient.listar(0, 15);
    }
}
