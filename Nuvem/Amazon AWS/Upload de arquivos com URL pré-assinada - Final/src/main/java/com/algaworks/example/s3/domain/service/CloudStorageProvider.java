package com.algaworks.example.s3.domain.service;

import com.algaworks.example.s3.domain.model.FileReference;

import java.net.URL;

public interface CloudStorageProvider {
    URL generatePresignedUploadUrl(FileReference fileReference);
    URL generatePresignedDownloadUrl(FileReference fileReference);
    boolean fileExists(String filePath);
    void moveFile(String fromPath, String toPath);
    void removeFile(String filePath);
}
