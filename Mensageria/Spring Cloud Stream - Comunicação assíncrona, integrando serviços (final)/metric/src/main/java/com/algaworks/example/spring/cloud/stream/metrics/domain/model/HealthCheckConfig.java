package com.algaworks.example.spring.cloud.stream.metrics.domain.model;

import lombok.*;
import org.apache.commons.lang3.Validate;

import javax.persistence.Embeddable;

@Getter
@Builder
@Embeddable
public class HealthCheckConfig {
    private CheckType type;
    private Integer timeout;

    private HealthCheckConfig() {

    }

    public HealthCheckConfig(CheckType type, Integer timeout) {
        Validate.notNull(type);
        Validate.notNull(timeout);
        this.type = type;
        this.timeout = timeout;
    }

    public enum CheckType {
        HTTP,
        PING;
    }
}
