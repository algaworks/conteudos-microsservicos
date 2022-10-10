package com.algaworks.example.spring.cloud.stream.scheduler.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthCheckTaskRequest {
    private UUID id;
    private AppModel app;
    private HealthCheckConfigModel checkConfig;
    private OffsetDateTime createdAt;
    private UUID scheduleId;
}
