package com.example.algaworks.calendar.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Builder
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date date;

    protected Reminder() {
        //JPA / Hibernate
    }

    public Reminder(Long id,
                    String title,
                    String description,
                    Date date) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(description);
        Objects.requireNonNull(date);

        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public void update(Reminder reminder) {
        this.title = reminder.title;
        this.description = reminder.description;
        this.date = reminder.date;
    }
}
