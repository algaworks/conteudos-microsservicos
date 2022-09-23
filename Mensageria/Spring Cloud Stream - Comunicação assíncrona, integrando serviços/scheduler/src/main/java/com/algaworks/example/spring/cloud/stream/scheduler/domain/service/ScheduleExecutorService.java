package com.algaworks.example.spring.cloud.stream.scheduler.domain.service;

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

    @Scheduled(cron = "0 */1 * * * *")
    public void doExecuteEachOneMinuteSchedule() {
        log.info("Executando check de um minuto");
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void doExecuteEachFiveMinutesSchedule() {
        log.info("Executando check de cinco minutos");
    }

    @Scheduled(cron = "0 */15 * * * *")
    public void doExecuteEachFifteenMinuteSchedule() {
        log.info("Executando check de quinze minutos");
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void doExecuteEachThirteenMinuteSchedule() {
        log.info("Executando check de trinta minutos");
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void doExecuteEachSixteenMinuteSchedule() {
        log.info("Executando check de sessenta minutos");
    }

}
