package com.algaworks.example.s3.api.controller;

import com.algaworks.example.s3.domain.model.FileReference;
import com.algaworks.example.s3.domain.repository.FileReferenceRepository;
import com.algaworks.example.s3.domain.service.DownloadRequestResult;
import com.algaworks.example.s3.domain.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.net.URL;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class DownloadController {

    private final FileReferenceRepository fileReferenceRepository;
    private final StorageService storageService;

    @GetMapping("/downloads/{fileReferenceId}/{fileName}")
    public ResponseEntity<Void> downloadRequest(@PathVariable UUID fileReferenceId) {
        FileReference fileReference = fileReferenceRepository.findById(fileReferenceId)
                .orElseThrow(EntityNotFoundException::new);

        if (fileReference.isPublicAccessible()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DownloadRequestResult requestResult = storageService.generateDownloadUrl(fileReference);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", requestResult.getDownloadSignedUrl());
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
