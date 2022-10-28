package com.algaworks.example.s3.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class EbookRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    private UUID coverId;
    @NotNull
    private UUID attachmentId;
}
