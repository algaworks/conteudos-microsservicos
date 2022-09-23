package com.algaworks.example.spring.cloud.stream.metrics.domain.service;

import com.algaworks.example.spring.cloud.stream.metrics.domain.model.AppMetric;
import com.algaworks.example.spring.cloud.stream.metrics.domain.model.AppMetricEvent;
import com.algaworks.example.spring.cloud.stream.metrics.domain.model.HealthCheckTaskResult;
import com.algaworks.example.spring.cloud.stream.metrics.domain.repository.AppMetricEventRepository;
import com.algaworks.example.spring.cloud.stream.metrics.domain.repository.AppMetricRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MetricService {

    private final AppMetricRepository metrics;
    private final AppMetricEventRepository events;

    public void process(HealthCheckTaskResult actualResult) {
        Validate.notNull(actualResult);
        log.info("Atualizando métricas da task " + actualResult.getTaskId());

        AppMetricEvent actualMetricEvent = events.saveAndFlush(AppMetricEvent.of(actualResult));

        var possibleLastMetricEvent =
                events.findFirstByCreatedAtBeforeAndCheckTypeAndIdNotOrderByCreatedAtDesc(actualResult.getCreatedAt(),
                        actualResult.getCheckConfig().getType(),
                        actualResult.getId());

        AppMetric metric = metrics.findOneByAppId(actualResult.getAppId()).orElseGet(()-> AppMetric.of(actualResult));

        possibleLastMetricEvent.ifPresent(latMetricEvent -> metric.processResult(latMetricEvent, actualMetricEvent));

        metrics.saveAndFlush(metric);
    }

}
