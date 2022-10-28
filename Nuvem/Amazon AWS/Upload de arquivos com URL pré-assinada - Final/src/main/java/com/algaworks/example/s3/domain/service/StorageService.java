package com.algaworks.example.s3.domain.service;

import com.algaworks.example.s3.domain.exception.StorageCloudException;
import com.algaworks.example.s3.domain.model.FileReference;
import com.algaworks.example.s3.domain.repository.FileReferenceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URL;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class StorageService {

    private final CloudStorageProvider cloudStorageProvider;
    private final FileReferenceRepository fileReferenceRepository;

    public UploadRequestResult generateUploadUrl(FileReference fileReference) {
        Objects.requireNonNull(fileReference);
        fileReferenceRepository.save(fileReference);
        URL presignedUploadUrl = cloudStorageProvider.generatePresignedUploadUrl(fileReference);
        return new UploadRequestResult(fileReference.getId(), presignedUploadUrl.toString());
    }

    public DownloadRequestResult generateDownloadUrl(FileReference fileReference) {
        Objects.requireNonNull(fileReference);
        URL url = cloudStorageProvider.generatePresignedDownloadUrl(fileReference);
        return new DownloadRequestResult(url.toString());
    }

    public boolean fileExists(FileReference fileReference) {
        Objects.requireNonNull(fileReference);
        return this.cloudStorageProvider.fileExists(fileReference.getPath());
    }

    public void softDelete(FileReference fileReference) {
        this.cloudStorageProvider.moveFile(
                fileReference.getPath(),
                "deleted/" + fileReference.getPath()
        );
    }

    @Scheduled(fixedRate = 1000L)
    @Transactional
    public void removeOldTempFiles() {
        List<FileReference> fileReferences = this.fileReferenceRepository.findAllByTempIsTrueAndCreatedAtBefore(
                OffsetDateTime.now().minus(Duration.ofDays(1)));

        for (FileReference fileReference : fileReferences) {
            this.fileReferenceRepository.delete(fileReference);
            this.fileReferenceRepository.flush();
            try {
                this.cloudStorageProvider.removeFile(fileReference.getPath());
            } catch (StorageCloudException e) {
                log.warn(e.getMessage());
            }
        }

    }
}
