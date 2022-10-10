package com.algaworks.example.spring.cloud.stream.metrics.domain.model;

import lombok.*;
import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class HealthCheckTaskResult {

    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID taskId;

    @Column(columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID scheduleId;

    private OffsetDateTime createdAt;
    @Embedded
    private HealthCheckConfig checkConfig;
    private Status status;
    private Integer responseTime;

    @Column(columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID appId;

    private HealthCheckTaskResult() {
    }

    public HealthCheckTaskResult(UUID id, UUID taskId, UUID scheduleId, OffsetDateTime createdAt,
                                 HealthCheckConfig checkConfig, Status status, Integer responseTime, UUID appId) {
        Validate.notNull(id);
        Validate.notNull(taskId);
        Validate.notNull(scheduleId);
        Validate.notNull(createdAt);
        Validate.notNull(checkConfig);
        Validate.notNull(status);
        Validate.notNull(responseTime);
        Validate.notNull(appId);
        this.id = id;
        this.taskId = taskId;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.checkConfig = checkConfig;
        this.status = status;
        this.responseTime = responseTime;
        this.appId = appId;
    }

    public enum Status {
        UP,DOWN,UNKNOWN
    }
}
