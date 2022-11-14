package com.algaworks.example.mensagem.api;

import javax.validation.constraints.NotBlank;

public class MensagemInput {

    @NotBlank
    private String conteudo;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
