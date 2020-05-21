package com.example.effort.task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();
    void insertAll(List<Task> tasks);

}
