package com.algaworks.example.ratelimiter.mensagem.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    @JsonProperty("totalPages")
    private long totalPaginas;
    @JsonProperty("totalElements")
    private long totalElementos;
    @JsonProperty("content")
    private List<T> conteudo = new ArrayList<T>();

    public long getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(long totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public List<T> getConteudo() {
        return conteudo;
    }

    public void setConteudo(List<T> conteudo) {
        this.conteudo = conteudo;
    }

}
