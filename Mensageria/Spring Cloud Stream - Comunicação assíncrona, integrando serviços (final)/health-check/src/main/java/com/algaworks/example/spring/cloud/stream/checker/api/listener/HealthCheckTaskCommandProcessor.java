package com.algaworks.example.spring.cloud.stream.checker.api.listener;

import com.algaworks.example.spring.cloud.stream.checker.api.model.HealthCheckTaskRequest;
import com.algaworks.example.spring.cloud.stream.checker.api.model.HealthCheckTaskResultModel;
import com.algaworks.example.spring.cloud.stream.checker.core.Mapper;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTaskResult;
import com.algaworks.example.spring.cloud.stream.checker.domain.service.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class HealthCheckTaskCommandProcessor
        implements Function<HealthCheckTaskRequest, HealthCheckTaskResultModel> {

    private final HealthCheckService healthCheckService;
    private final Mapper mapper;

    @Override
    public HealthCheckTaskResultModel apply(HealthCheckTaskRequest request) {
        HealthCheckTaskResult result = healthCheckService.checkIfIsUp(request.toDomain());
        return mapper.map(result, HealthCheckTaskResultModel.class);
    }
}
