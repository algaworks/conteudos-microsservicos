package com.algaworks.example.s3.api.assembler;

import com.algaworks.example.s3.api.model.EbookRequest;
import com.algaworks.example.s3.domain.exception.BusinessException;
import com.algaworks.example.s3.domain.model.Ebook;
import com.algaworks.example.s3.domain.model.FileReference;
import com.algaworks.example.s3.domain.repository.FileReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EbookModelDissembler {

    private final FileReferenceRepository fileReferenceRepository;

    public Ebook toDomain(EbookRequest request) {
        return Ebook.builder()
                .title(request.getTitle())
                .autor(request.getAuthor())
                .cover(findFileReferenceById(request.getCoverId()))
                .attachment(findFileReferenceById(request.getAttachmentId()))
                .build();
    }

    public Ebook toDomain(EbookRequest request, UUID ebookId) {
        return Ebook.builder()
                .id(ebookId)
                .title(request.getTitle())
                .autor(request.getAuthor())
                .cover(findFileReferenceById(request.getCoverId()))
                .attachment(findFileReferenceById(request.getAttachmentId()))
                .build();
    }

    private FileReference findFileReferenceById(UUID id) {
        return fileReferenceRepository.findById(id)
                .orElseThrow(() -> new BusinessException(String.format("Arquivo não encontrado %s ", id)));
    }
}
