package com.example.algaworks.calendar.api;

import com.example.algaworks.calendar.domain.Reminder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ReminderInput {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Date date;

    public Reminder toDomain() {
        return toDomain(null);
    }

    public Reminder toDomain(Long reminderId) {
        return Reminder.builder()
                .id(reminderId)
                .title(title)
                .description(description)
                .date(date)
                .build();
    }

}
