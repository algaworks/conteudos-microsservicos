package com.algaworks.example.spring.cloud.stream.scheduler.api.model;

import com.algaworks.example.spring.cloud.stream.scheduler.api.validators.ValidInterval;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ScheduleUpdateRequest {
    @ValidInterval
    private Integer runInterval;
    @NotNull
    @Valid
    private HealthCheckConfigModel checkConfig;
    @NotNull
    private UUID appId;
}
