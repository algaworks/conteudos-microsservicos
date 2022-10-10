package com.algaworks.example.spring.cloud.stream.metrics.domain.repository;

import com.algaworks.example.spring.cloud.stream.metrics.domain.model.AppMetricEvent;
import com.algaworks.example.spring.cloud.stream.metrics.domain.model.HealthCheckConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AppMetricEventRepository extends JpaRepository<AppMetricEvent, UUID> {
    Optional<AppMetricEvent> findFirstByCreatedAtBeforeAndCheckTypeAndIdNotOrderByCreatedAtDesc(OffsetDateTime createdAt,
                                                                                HealthCheckConfig.CheckType type,
                                                                                UUID id);
}
