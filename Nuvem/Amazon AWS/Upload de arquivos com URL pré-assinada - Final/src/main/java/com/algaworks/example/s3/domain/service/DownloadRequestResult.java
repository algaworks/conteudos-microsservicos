package com.algaworks.example.s3.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DownloadRequestResult {
    private String downloadSignedUrl;
}
