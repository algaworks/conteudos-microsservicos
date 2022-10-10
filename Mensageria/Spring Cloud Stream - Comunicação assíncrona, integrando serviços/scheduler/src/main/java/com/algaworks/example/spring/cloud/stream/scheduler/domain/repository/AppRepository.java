package com.algaworks.example.spring.cloud.stream.scheduler.domain.repository;

import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppRepository extends JpaRepository<App, UUID> {
}
