package com.example.effort.time;

import com.example.effort.category.Category;
import com.example.effort.user.User;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class FinishedTimeEntry extends TimeEntry {

    public FinishedTimeEntry() {
    }

    public FinishedTimeEntry(User user, LocalDate date, Category category, Integer startTime, Integer endTime) {
        super(user, date, category, startTime, endTime);
    }
}
