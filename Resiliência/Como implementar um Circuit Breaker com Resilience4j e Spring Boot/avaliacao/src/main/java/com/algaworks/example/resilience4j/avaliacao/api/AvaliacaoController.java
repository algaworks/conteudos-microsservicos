package com.algaworks.example.resilience4j.avaliacao.api;

import com.algaworks.example.resilience4j.avaliacao.domain.AvaliacaoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacaos")
public class AvaliacaoController {
	
	private final AvaliacaoRepository avaliacaos;

	public AvaliacaoController(AvaliacaoRepository avaliacaos) {
		this.avaliacaos = avaliacaos;
	}

	@GetMapping
	public List<AvaliacaoModel> buscarPorProduto(@RequestParam Long produtoId) {
		return avaliacaos.getAll()
				.stream()
				.filter(avaliacao -> avaliacao.getProdutoId().equals(produtoId))
				.map(AvaliacaoModel::of)
				.collect(Collectors.toList());
	}
}
