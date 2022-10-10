package com.algaworks.example.spring.cloud.stream.metrics.api.model;

import com.algaworks.example.spring.cloud.stream.metrics.domain.model.HealthCheckConfig;
import com.algaworks.example.spring.cloud.stream.metrics.domain.model.HealthCheckTaskResult;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AppMetricEventModel {
    private UUID id;
    private UUID scheduleId;
    private UUID appId;
    private OffsetDateTime createdAt;
    private HealthCheckConfig.CheckType checkType;
    private HealthCheckTaskResult.Status resultStatus;
}
