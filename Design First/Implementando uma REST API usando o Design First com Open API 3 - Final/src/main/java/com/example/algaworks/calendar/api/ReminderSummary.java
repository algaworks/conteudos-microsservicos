package com.example.algaworks.calendar.api;

import com.example.algaworks.calendar.domain.Reminder;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReminderSummary {
    private Long id;
    private String title;
    private Date date;

    public static ReminderSummary of(Reminder reminder) {
        return ReminderSummary.builder()
                .id(reminder.getId())
                .title(reminder.getTitle())
                .date(reminder.getDate())
                .build();
    }
}
