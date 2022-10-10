package com.algaworks.example.spring.cloud.stream.metrics.api.listener;

import com.algaworks.example.spring.cloud.stream.metrics.api.model.HealthCheckTaskResultModel;
import com.algaworks.example.spring.cloud.stream.metrics.domain.model.HealthCheckTaskResult;
import com.algaworks.example.spring.cloud.stream.metrics.domain.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class HealthCheckResultEventListener implements Consumer<HealthCheckTaskResultModel> {

    private final MetricService metricService;

    @Override
    public void accept(HealthCheckTaskResultModel model) {
        HealthCheckTaskResult taskResult = model.toDomain();
        metricService.process(taskResult);
    }
}
