package com.algaworks.example.spring.cloud.stream.checker.domain.model.valueobject;

import lombok.*;
import org.apache.commons.lang3.Validate;

@Getter
@Builder
public class HealthCheckConfig {
    private HealthCheckType type;
    private Integer timeout;

    public HealthCheckConfig(HealthCheckType type, Integer timeout) {
        Validate.notNull(type);
        Validate.notNull(timeout);
        this.type = type;
        this.timeout = timeout;
    }
}
