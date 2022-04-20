package com.algaworks.example.resilience4j.avaliacao.infra.database;

import com.algaworks.example.resilience4j.avaliacao.domain.Avaliacao;
import com.algaworks.example.resilience4j.avaliacao.domain.AvaliacaoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {
	
	private static final List<Avaliacao> AVALIACOES = new ArrayList<>();
	private static long id = 1;
	
	//Inicializador dos statics
	static {
		AVALIACOES.add(new Avaliacao(nextId(), 10, "Thiago", 
				"Produto superou minhas expectativas.", 1L));
		AVALIACOES.add(new Avaliacao(nextId(), 1, "Alexandre",
				"Veio com feito.", 1L));
		AVALIACOES.add(new Avaliacao(nextId(), 4, "Maria",
				"Computador trava muito.", 1L));
		
		AVALIACOES.add(new Avaliacao(nextId(), 8, "Daniel",
				"Quase perfeito.", 2L));
		AVALIACOES.add(new Avaliacao(nextId(), 5, "Alex",
				"Não vem com sistema operacional.", 3L));
	}
	
	@Override
	public void save(Avaliacao avaliacao) {
		avaliacao.setId(nextId());
		AVALIACOES.add(avaliacao);
	}

	@Override
	public Optional<Avaliacao> getOne(Long id) {
		return AVALIACOES.stream().filter(e -> e.getId().equals(id)).findFirst();
	}

	@Override
	public List<Avaliacao> getAll() {
		return new ArrayList<>(AVALIACOES);
	}

	private static long nextId() {
		return id++;
	}
}

