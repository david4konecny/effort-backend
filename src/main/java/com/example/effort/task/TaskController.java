package com.example.effort.task;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public List<Task> getTasks(@RequestParam(required = false) String date) {
        if (date == null)
            return taskService.getAllTasks();
        else
            return taskService.getTasksByDate(date);
    }

    @PostMapping("")
    public Task addTask(@RequestBody Task task) {
        return taskService.add(task);
    }

    @PutMapping("")
    public Task editTask(@RequestBody Task task) {
        return taskService.edit(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @GetMapping("/statsByDate")
    public List<DateAndTasksView> taskSummariesByDateForPeriod(
            @RequestParam String startDate, @RequestParam String endDate
    ) {
        return taskService.getTaskSummariesByDateForPeriod(startDate, endDate);
    }

}
