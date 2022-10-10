package com.algaworks.example.spring.cloud.stream.checker.api.model;

import com.algaworks.example.spring.cloud.stream.checker.domain.model.App;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTask;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.valueobject.HealthCheckConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthCheckTaskRequest {
    private UUID id;
    private AppModel app;
    private HealthCheckConfigModel checkConfig;
    private OffsetDateTime createdAt;
    private UUID scheduleId;

    public HealthCheckTask toDomain() {
        HealthCheckConfigModel checkConfig = this.getCheckConfig();
        AppModel app = this.getApp();
        return HealthCheckTask.builder()
                .id(this.getId())
                .createdAt(this.getCreatedAt())
                .scheduleId(this.getScheduleId())
                .checkConfig(HealthCheckConfig.builder()
                        .timeout(checkConfig.getTimeout())
                        .type(checkConfig.getType())
                        .build())
                .app(App.builder()
                        .id(app.getId())
                        .name(app.getName())
                        .address(app.getAddress())
                        .build())
                .build();
    }
}
