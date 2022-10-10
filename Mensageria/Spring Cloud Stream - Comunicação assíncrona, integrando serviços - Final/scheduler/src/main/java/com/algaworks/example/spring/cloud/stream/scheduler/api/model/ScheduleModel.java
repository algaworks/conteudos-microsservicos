package com.algaworks.example.spring.cloud.stream.scheduler.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleModel {
    private UUID id;
    private HealthCheckConfigModel checkConfig;
    private Integer interval;
    private AppModel app;
}
