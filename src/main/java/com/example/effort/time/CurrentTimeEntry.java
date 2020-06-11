package com.example.effort.time;

import com.example.effort.category.Category;
import com.example.effort.user.User;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class CurrentTimeEntry extends TimeEntry {

    public CurrentTimeEntry() {
    }

    public CurrentTimeEntry(User user, LocalDate date, Category category, Integer startTime, Integer endTime) {
        super(user, date, category, startTime, endTime);
    }
}
