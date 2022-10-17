package com.example.algaworks.calendar.api;

import com.example.algaworks.calendar.domain.Reminder;
import com.example.algaworks.calendar.domain.ReminderFilter;
import com.example.algaworks.calendar.domain.ReminderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reminders")
@AllArgsConstructor
public class ReminderController {

    private final ReminderRepository reminders;

    @GetMapping
    public List<ReminderSummary> getAll(ReminderFilter filter) {
        List<Reminder> reminderList = this.reminders.findAll();
        return reminderList.stream().map(ReminderSummary::of).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderDetail create(@RequestBody @Valid ReminderInput input) {
        Reminder reminder = input.toDomain();
        this.reminders.save(reminder);
        return ReminderDetail.of(reminder);
    }

    @GetMapping("{reminderId}")
    public ReminderDetail getOne(@PathVariable Long reminderId) {
        Reminder reminder = findOrFail(reminderId);
        return ReminderDetail.of(reminder);
    }

    @PutMapping("{reminderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid ReminderInput input,
                       @PathVariable Long reminderId) {
        Reminder actualReminder = findOrFail(reminderId);
        actualReminder.update(input.toDomain(reminderId));
        reminders.save(actualReminder);
    }

    @DeleteMapping("{reminderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long reminderId) {
        Reminder reminder = findOrFail(reminderId);
        reminders.delete(reminder);
    }

    private Reminder findOrFail(Long reminderId) {
        return this.reminders.findById(reminderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lembrete não encontrado"));
    }

}
