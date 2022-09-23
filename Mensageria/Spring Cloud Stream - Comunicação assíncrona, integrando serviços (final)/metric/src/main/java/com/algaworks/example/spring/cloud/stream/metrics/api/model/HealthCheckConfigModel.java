package com.algaworks.example.spring.cloud.stream.metrics.api.model;

import com.algaworks.example.spring.cloud.stream.metrics.domain.model.HealthCheckConfig;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HealthCheckConfigModel {
    @NotNull
    private HealthCheckConfig.CheckType type;
    @NotNull
    private Integer timeout;

    public HealthCheckConfig toDomain() {
        return HealthCheckConfig.builder()
                .type(this.getType())
                .timeout(this.getTimeout())
                .build();
    }
}
