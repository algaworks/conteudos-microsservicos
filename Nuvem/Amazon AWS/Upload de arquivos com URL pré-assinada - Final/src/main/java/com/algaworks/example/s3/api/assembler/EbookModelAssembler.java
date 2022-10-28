package com.algaworks.example.s3.api.assembler;

import com.algaworks.example.s3.api.model.EbookModel;
import com.algaworks.example.s3.api.model.FileReferenceModel;
import com.algaworks.example.s3.core.properties.StorageProperties;
import com.algaworks.example.s3.domain.model.Ebook;
import com.algaworks.example.s3.domain.model.FileReference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
@RequiredArgsConstructor
public class EbookModelAssembler {

    private final StorageProperties storageProperties;

    public EbookModel toModel(Ebook ebook) {
        EbookModel.EbookModelBuilder builder = EbookModel.builder()
                .id(ebook.getId())
                .author(ebook.getAutor())
                .title(ebook.getTitle());

        if(ebook.getCover() != null) {
            builder.cover(createCover(ebook.getCover()));
        }

        if(ebook.getAttachment() != null) {
            builder.attachment(createAttchment(ebook.getAttachment()));
        }

        return builder.build();
    }

    private FileReferenceModel createCover(FileReference cover) {
        String downloadUrl = storageProperties.getImage().getDownloadUrl().toString() + "/" + cover.getPath();
        return FileReferenceModel.builder()
                .id(cover.getId())
                .name(cover.getName())
                .contentType(cover.getContentType())
                .contentLength(cover.getContentLength())
                .publicAccessible(cover.isPublicAccessible())
                .downloadUrl(downloadUrl)
                .build();
    }

    private FileReferenceModel createAttchment(FileReference attachment) {
        String downloadUrl = storageProperties.getDocument().getDownloadUrl().toString() + "/" + attachment.getPath();
        return FileReferenceModel.builder()
                .id(attachment.getId())
                .name(attachment.getName())
                .contentType(attachment.getContentType())
                .contentLength(attachment.getContentLength())
                .publicAccessible(attachment.isPublicAccessible())
                .downloadUrl(downloadUrl)
                .build();
    }
}
