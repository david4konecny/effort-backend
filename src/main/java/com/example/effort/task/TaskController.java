package com.example.effort.task;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(@RequestParam(required = false) String date) {
        if (date == null)
            return taskService.getAllTasks();
        else
            return taskService.getTasksByDate(date);
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return taskService.add(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

}
