package com.algaworks.example.s3.domain.service;

import com.algaworks.example.s3.domain.exception.BusinessException;
import com.algaworks.example.s3.domain.model.Ebook;
import com.algaworks.example.s3.domain.model.FileReference;
import com.algaworks.example.s3.domain.repository.EbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EbookService {

    private final EbookRepository ebookRepository;
    private final StorageService storageService;

    @Transactional
    public Ebook create(Ebook ebook) {
        Objects.requireNonNull(ebook);

        validate(ebook);

        ebook.getCover().setTemp(false);
        ebook.getAttachment().setTemp(false);

        ebookRepository.save(ebook);

        return ebook;
    }

    @Transactional
    public Ebook update(Ebook ebookUpdated) {
        Objects.requireNonNull(ebookUpdated);

        validate(ebookUpdated);

        Ebook existingEbook = ebookRepository.findById(ebookUpdated.getId()).orElseThrow(EntityNotFoundException::new);

        if(!ebookUpdated.getCover().equals(existingEbook.getCover())) {
            ebookUpdated.getCover().setTemp(false);
            this.storageService.softDelete(existingEbook.getCover());
        }

        if(!ebookUpdated.getAttachment().equals(existingEbook.getAttachment())) {
            ebookUpdated.getAttachment().setTemp(false);
            this.storageService.softDelete(existingEbook.getAttachment());
        }

        existingEbook.update(ebookUpdated);
        ebookRepository.save(existingEbook);
        ebookRepository.flush();

        return existingEbook;
    }

    private void validate(Ebook ebook) {
        if(!storageService.fileExists(ebook.getCover())) {
            throw new BusinessException(String.format("Arquivo %s não encontrado", ebook.getCover().getId()));
        }

        if(!storageService.fileExists(ebook.getAttachment())) {
            throw new BusinessException(String.format("Arquivo %s não encontrado", ebook.getAttachment().getId()));
        }

        if(!FileReference.Type.IMAGE.equals(ebook.getCover().getType())) {
            throw new BusinessException(String.format("Arquivo de capa %s deve ser uma imagem",
                    ebook.getCover().getId()));
        }

        if(!FileReference.Type.DOCUMENT.equals(ebook.getAttachment().getType())) {
            throw new BusinessException(String.format("Arquivo de anexo %s deve ser um documento",
                    ebook.getAttachment().getId()));
        }
    }
}
