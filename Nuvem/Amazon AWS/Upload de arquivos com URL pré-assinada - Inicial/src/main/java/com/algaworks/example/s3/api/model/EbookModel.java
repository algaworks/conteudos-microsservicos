package com.algaworks.example.s3.api.model;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class EbookModel {
    private UUID id;
    private String title;
    private String author;
}
