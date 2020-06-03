package com.example.effort.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDate(LocalDate date);

    @Query("select new com.example.effort.task.DateAndTasksView(t.date, count(t), sum(case when t.finished = true then 1 else 0 end)) from Task t where t.date >= ?1 and t.date <= ?2 group by t.date")
    List<DateAndTasksView> getTaskSummariesByDate(LocalDate startDate, LocalDate endDate);

}
