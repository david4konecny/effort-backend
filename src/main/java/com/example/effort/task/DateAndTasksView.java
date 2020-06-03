package com.example.effort.task;

import java.time.LocalDate;

public class DateAndTasksView {
    private LocalDate date;
    private Long count;
    private Long finished;

    public DateAndTasksView() {
    }

    public DateAndTasksView(LocalDate date, Long count, Long finished) {
        this.date = date;
        this.count = count;
        this.finished = finished;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFinished() {
        return finished;
    }

    public void setFinished(Long finished) {
        this.finished = finished;
    }
}
