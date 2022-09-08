package com.algaworks.example.bulkhead.producer.api;


import com.algaworks.example.bulkhead.producer.domain.Encomenda;
import com.algaworks.example.bulkhead.producer.domain.EncomendaRepository;
import com.algaworks.example.bulkhead.producer.domain.EncomendaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/encomendas")
public class EncomendaController {
	
	private final EncomendaRepository encomendaRepository;
	private final EncomendaService encomendaService;

	public EncomendaController(EncomendaRepository encomendaRepository,
							   EncomendaService encomendaService) {
		this.encomendaRepository = encomendaRepository;
		this.encomendaService = encomendaService;
	}

	@GetMapping
	public Page<Encomenda> buscarPaginado(Pageable pageable) {
		return encomendaRepository.findAll(pageable);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Encomenda criarNova(@RequestBody @Valid Encomenda encomenda) {
		return encomendaService.criarNova(encomenda);
	}

	@GetMapping("/{entregaId}")
	public Encomenda buscarPorId(@PathVariable Long entregaId) {
		return encomendaRepository.findById(entregaId).orElseThrow(RecursoNaoEncontradoException::new);
	}

	@PostMapping("/{entregaId}/marcar-como-em-andamento")
	public void marcarComoEmAndamento(@PathVariable Long entregaId) {
		encomendaService.marcarComoEmAndamento(entregaId);
	}

	@PostMapping("/{entregaId}/marcar-como-entregue")
	public void marcarComoEntregue(@PathVariable Long entregaId) {
		encomendaService.marcarComoEntregue(entregaId);
	}
}
