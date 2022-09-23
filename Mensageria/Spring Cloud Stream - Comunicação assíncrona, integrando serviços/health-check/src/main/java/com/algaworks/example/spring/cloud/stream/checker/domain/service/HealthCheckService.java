package com.algaworks.example.spring.cloud.stream.checker.domain.service;

import com.algaworks.example.spring.cloud.stream.checker.domain.model.App;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTaskResult;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTask;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.valueobject.HealthCheckType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Slf4j
public class HealthCheckService {

    public HealthCheckTaskResult checkIfIsUp(HealthCheckTask task) {
        Validate.notNull(task);
        App app = task.getApp();
        log.info("Checando app: "  + app.getAddress());

        HealthCheckType type = task.getCheckConfig().getType();
        HealthResult result = type.getHealthChecker().execute(task);

        return HealthCheckTaskResult.of(task, result);
    }
}
