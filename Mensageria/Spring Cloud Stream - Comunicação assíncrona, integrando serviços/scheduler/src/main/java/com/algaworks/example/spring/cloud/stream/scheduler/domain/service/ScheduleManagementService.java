package com.algaworks.example.spring.cloud.stream.scheduler.domain.service;

import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.Schedule;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.repository.ScheduleRepository;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.service.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleManagementService {

    private final ScheduleRepository schedules;

    @Transactional
    public Schedule create(Schedule schedule) {
        Validate.notNull(schedule);
        Validate.notNull(schedule.getApp());
        return schedules.saveAndFlush(schedule);
    }

    @Transactional
    public Schedule update(Schedule updatedSchedule) {
        Validate.notNull(updatedSchedule);
        Validate.notNull(updatedSchedule.getApp());

        Schedule schedule = schedules.findById(updatedSchedule.getId()).orElseThrow(ScheduleNotFoundException::new);

        schedule.update(updatedSchedule);

        return schedules.saveAndFlush(schedule);
    }

    @Transactional
    public void delete(UUID scheduleId) {
        Schedule schedule = schedules.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
        schedules.delete(schedule);
    }
}
