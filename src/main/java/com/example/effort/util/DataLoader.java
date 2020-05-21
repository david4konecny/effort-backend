package com.example.effort.util;

import com.example.effort.task.Task;
import com.example.effort.task.TaskService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private TaskService taskService;

    public DataLoader(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addSampleData();
    }

    private void addSampleData() {
        List<Task> tasks = new ArrayList<>();
        Collections.addAll(tasks,
                new Task(LocalDate.now(), "write a report"),
                new Task(LocalDate.now(), "clean dog")
        );
        taskService.insertAll(tasks);
    }

}
