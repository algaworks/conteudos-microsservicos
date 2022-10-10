package com.algaworks.example.spring.cloud.stream.checker.api.processor;

import com.algaworks.example.spring.cloud.stream.checker.api.model.HealthCheckTaskRequest;
import com.algaworks.example.spring.cloud.stream.checker.api.model.HealthCheckTaskResultModel;
import com.algaworks.example.spring.cloud.stream.checker.core.Mapper;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTask;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTaskResult;
import com.algaworks.example.spring.cloud.stream.checker.domain.service.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class HealthCheckTaskCommandProcessor
        implements Function<HealthCheckTaskRequest, HealthCheckTaskResultModel> {

    private final Mapper mapper;
    private final HealthCheckService healthCheckService;

    @Override
    public HealthCheckTaskResultModel apply(HealthCheckTaskRequest request) {
        HealthCheckTask task = request.toDomain();
        HealthCheckTaskResult result = healthCheckService.checkIfIsUp(task);
        return mapper.map(result, HealthCheckTaskResultModel.class);
    }
}
