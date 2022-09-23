package com.algaworks.example.spring.cloud.stream.scheduler.domain.service;

import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.HealthCheckTask;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleExecutorService {

    private final ScheduleRepository schedules;
    private final ScheduleEventGateway gateway;

    @Scheduled(cron = "0 */1 * * * *")
    public void doExecuteEachOneMinuteSchedule() {
        log.info("Executando check de um minuto");
        schedules.findAllByRunInterval(1).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            gateway.sendExecuteTaskCommand(task);
        });
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void doExecuteEachFiveMinutesSchedule() {
        log.info("Executando check de cinco minutos");
        schedules.findAllByRunInterval(5).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            gateway.sendExecuteTaskCommand(task);
        });
    }

    @Scheduled(cron = "0 */15 * * * *")
    public void doExecuteEachFifteenMinuteSchedule() {
        log.info("Executando check de quinze minutos");
        schedules.findAllByRunInterval(15).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            gateway.sendExecuteTaskCommand(task);
        });
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void doExecuteEachThirteenMinuteSchedule() {
        log.info("Executando check de trinta minutos");
        schedules.findAllByRunInterval(30).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            gateway.sendExecuteTaskCommand(task);
        });
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void doExecuteEachSixteenMinuteSchedule() {
        log.info("Executando check de sessenta minutos");
        schedules.findAllByRunInterval(60).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            gateway.sendExecuteTaskCommand(task);
        });
    }

}
