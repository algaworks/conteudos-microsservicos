package com.algaworks.example.spring.cloud.stream.scheduler.domain.repository;

import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findAllByRunInterval(Integer runInterval);
}
