package com.algaworks.example.spring.cloud.stream.metrics.domain.repository;

import com.algaworks.example.spring.cloud.stream.metrics.domain.model.AppMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppMetricRepository extends JpaRepository<AppMetric, UUID> {
    Optional<AppMetric> findOneByAppId(UUID appId);
}
