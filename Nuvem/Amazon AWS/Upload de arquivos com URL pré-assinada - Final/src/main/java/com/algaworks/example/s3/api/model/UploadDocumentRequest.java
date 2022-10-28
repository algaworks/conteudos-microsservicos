package com.algaworks.example.s3.api.model;

import com.algaworks.example.s3.domain.model.FileReference;
import com.algaworks.example.s3.validation.AllowedContentTypes;
import com.algaworks.example.s3.validation.AllowedFileExtensions;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UploadDocumentRequest {

    @NotBlank
    @AllowedFileExtensions("pdf")
    private String fileName;

    @NotBlank
    @AllowedContentTypes("application/pdf")
    private String contentType;

    @NotNull
    @Min(1)
    private Long contentLength;

    public FileReference toDomain() {
        return FileReference.builder()
                .name(this.fileName)
                .contentType(this.contentType)
                .contentLength(this.contentLength)
                .type(FileReference.Type.DOCUMENT)
                .build();
    }
}
