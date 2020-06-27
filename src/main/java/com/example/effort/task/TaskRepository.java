package com.example.effort.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.date = ?1 and t.user.id = ?#{ principal?.id } order by t.position")
    List<Task> findByDate(LocalDate date);

    @Query("select new com.example.effort.task.DateAndTasksView(t.date, count(t), sum(case when t.finished = true then 1 else 0 end)) from Task t where t.date >= ?1 and t.date <= ?2 group by t.date")
    List<DateAndTasksView> getTaskSummariesByDate(LocalDate startDate, LocalDate endDate);

    @Modifying
    @Transactional
    @Query("delete from Task t where t.user.id = ?#{ principal?.id }")
    void deleteTasks();

}
