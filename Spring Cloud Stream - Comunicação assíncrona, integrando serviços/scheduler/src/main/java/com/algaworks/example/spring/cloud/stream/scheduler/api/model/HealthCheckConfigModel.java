package com.algaworks.example.spring.cloud.stream.scheduler.api.model;

import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.valueobject.HealthCheckConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthCheckConfigModel {
    @NotNull
    private HealthCheckConfig.Type type;
    @NotNull
    private Integer timeout;
}
