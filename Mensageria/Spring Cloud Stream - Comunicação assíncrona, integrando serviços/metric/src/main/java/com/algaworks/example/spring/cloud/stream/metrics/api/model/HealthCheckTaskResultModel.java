package com.algaworks.example.spring.cloud.stream.metrics.api.model;

import com.algaworks.example.spring.cloud.stream.metrics.domain.model.HealthCheckTaskResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class HealthCheckTaskResultModel {
    private UUID id;
    private UUID taskId;
    private UUID scheduleId;
    private UUID appId;
    private OffsetDateTime createdAt;
    private HealthCheckConfigModel checkConfig;
    private HealthCheckTaskResult.Status status;
    private Integer responseTime;

    public HealthCheckTaskResult toDomain() {
        return HealthCheckTaskResult.builder()
                .id(this.getId())
                .scheduleId(this.getScheduleId())
                .appId(this.getAppId())
                .taskId(this.getTaskId())
                .createdAt(this.getCreatedAt())
                .responseTime(this.getResponseTime())
                .status(this.getStatus())
                .checkConfig(checkConfig.toDomain())
                .build();
    }
}
