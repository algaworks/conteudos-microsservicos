package com.algaworks.example.s3.domain.repository;

import com.algaworks.example.s3.domain.model.FileReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface FileReferenceRepository extends JpaRepository<FileReference, UUID> {
    List<FileReference> findAllByTempIsTrueAndCreatedAtBefore(OffsetDateTime createdAt);
}
