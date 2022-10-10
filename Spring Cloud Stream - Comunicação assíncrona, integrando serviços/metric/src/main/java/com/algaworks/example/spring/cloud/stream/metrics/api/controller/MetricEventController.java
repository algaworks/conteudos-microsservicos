package com.algaworks.example.spring.cloud.stream.metrics.api.controller;

import com.algaworks.example.spring.cloud.stream.metrics.api.model.AppMetricEventModel;
import com.algaworks.example.spring.cloud.stream.metrics.api.model.HealthCheckTaskResultModel;
import com.algaworks.example.spring.cloud.stream.metrics.core.Mapper;
import com.algaworks.example.spring.cloud.stream.metrics.domain.repository.AppMetricEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/metrics/events")
@RequiredArgsConstructor
public class MetricEventController {
    private final AppMetricEventRepository results;
    private final Mapper mapper;

    @GetMapping
    public List<AppMetricEventModel> findAll() {
        return results.findAll()
                .stream()
                .map(metric -> mapper.map(metric, AppMetricEventModel.class))
                .toList();
    }
}
