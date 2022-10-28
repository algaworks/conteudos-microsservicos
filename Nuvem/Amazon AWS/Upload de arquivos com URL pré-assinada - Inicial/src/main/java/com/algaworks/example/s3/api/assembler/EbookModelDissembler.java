package com.algaworks.example.s3.api.assembler;

import com.algaworks.example.s3.api.model.EbookRequest;
import com.algaworks.example.s3.domain.model.Ebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EbookModelDissembler {

    public Ebook toDomain(EbookRequest request) {
        return Ebook.builder()
                .title(request.getTitle())
                .autor(request.getAuthor())
                .build();
    }

    public Ebook toDomain(EbookRequest request, UUID ebookId) {
        return Ebook.builder()
                .id(ebookId)
                .title(request.getTitle())
                .autor(request.getAuthor())
                .build();
    }
}
