package com.algaworks.example.spring.cloud.stream.checker.api.model;

import com.algaworks.example.spring.cloud.stream.checker.domain.model.valueobject.HealthCheckType;
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
    private HealthCheckType type;
    @NotNull
    private Integer timeout;
}
