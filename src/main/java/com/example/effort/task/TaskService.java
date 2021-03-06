package com.example.effort.task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();
    List<Task> getTasksByDate(String date);
    void insertAll(List<Task> tasks);
    Task add(Task task);
    int edit(Task task);
    void delete(Long id);
    List<DateAndTasksView> getTaskSummariesByDateForPeriod(String startDate, String endDate);

}
