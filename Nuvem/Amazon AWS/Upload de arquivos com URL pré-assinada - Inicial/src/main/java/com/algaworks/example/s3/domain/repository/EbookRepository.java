package com.algaworks.example.s3.domain.repository;

import com.algaworks.example.s3.domain.model.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EbookRepository extends JpaRepository<Ebook, UUID> {
}
