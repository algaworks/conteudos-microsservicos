package com.algaworks.example.resilience4j.produtos.client.avaliacoes;

import java.util.List;

public interface AvaliacaoClient {

	List<AvaliacaoModel> buscarTodosPorProduto(Long productId);
}
