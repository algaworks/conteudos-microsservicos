package com.example.algaworks.calendar.api;

import com.example.algaworks.calendar.domain.Reminder;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReminderDetail {
    private Long id;
    private String title;
    private String description;
    private Date date;

    public static ReminderDetail of(Reminder reminder) {
        return ReminderDetail.builder()
                .id(reminder.getId())
                .title(reminder.getTitle())
                .description(reminder.getDescription())
                .date(reminder.getDate())
                .build();
    }
}
