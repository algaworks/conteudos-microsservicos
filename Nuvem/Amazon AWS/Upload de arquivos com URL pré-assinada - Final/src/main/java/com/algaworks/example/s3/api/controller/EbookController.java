package com.algaworks.example.s3.api.controller;

import com.algaworks.example.s3.api.model.EbookRequest;
import com.algaworks.example.s3.api.model.EbookModel;
import com.algaworks.example.s3.api.assembler.EbookModelAssembler;
import com.algaworks.example.s3.api.assembler.EbookModelDissembler;
import com.algaworks.example.s3.domain.model.Ebook;
import com.algaworks.example.s3.domain.repository.EbookRepository;
import com.algaworks.example.s3.domain.service.EbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ebooks")
@RequiredArgsConstructor
public class EbookController {

    private final EbookService ebookService;
    private final EbookRepository ebookRepository;
    private final EbookModelAssembler ebookModelAssembler;
    private final EbookModelDissembler ebookModelDissembler;

    @PostMapping
    public EbookModel create(@RequestBody @Valid EbookRequest request) {
        Ebook ebook = ebookModelDissembler.toDomain(request);
        return ebookModelAssembler.toModel(ebookService.create(ebook));
    }

    @GetMapping("{ebookId}")
    public EbookModel getById(@PathVariable UUID ebookId) {
        return ebookModelAssembler.toModel(ebookRepository.findById(ebookId).orElseThrow(EntityNotFoundException::new));
    }

    @PutMapping("{ebookId}")
    public EbookModel atualizar(@PathVariable UUID ebookId,
                                @RequestBody @Valid EbookRequest request) {
        Ebook ebook = ebookModelDissembler.toDomain(request, ebookId);
        return  ebookModelAssembler.toModel(ebookService.update(ebook));
    }

    @GetMapping
    public List<EbookModel> list() {
        return ebookRepository.findAll()
                .stream()
                .map(ebookModelAssembler::toModel)
                .toList();
    }
}
