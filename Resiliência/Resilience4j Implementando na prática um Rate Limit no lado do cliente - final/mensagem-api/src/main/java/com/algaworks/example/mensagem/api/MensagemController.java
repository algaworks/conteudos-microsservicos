package com.algaworks.example.mensagem.api;


import com.algaworks.example.mensagem.domain.Mensagem;
import com.algaworks.example.mensagem.domain.MensagemRepository;
import com.algaworks.example.mensagem.security.SegurancaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {
	
	private final MensagemRepository mensagemRepository;
	private final SegurancaService segurancaService;

	public MensagemController(MensagemRepository mensagemRepository,
							  SegurancaService segurancaService) {
		this.mensagemRepository = mensagemRepository;
		this.segurancaService = segurancaService;
	}

	@GetMapping
	public Page<Mensagem> buscarPaginado(Pageable pageable) {
		return mensagemRepository.findAll(pageable);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mensagem criarNovo(@RequestBody @Valid MensagemInput mensagemInput) {
		return mensagemRepository.save(new Mensagem(
				segurancaService.getUsuario(),
				mensagemInput.getConteudo()
		));
	}

	@GetMapping("/{id}")
	public Mensagem buscarPorId(@PathVariable Long id) {
		return mensagemRepository.findById(id).orElseThrow(RecursoNaoEncontradoException::new);
	}
}
