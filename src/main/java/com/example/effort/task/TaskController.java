package com.example.effort.task;

import com.example.effort.user.User;
import com.example.effort.util.exceptions.DataNotValidException;
import com.example.effort.util.exceptions.UpdateFailedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Task addTask(@RequestBody @Valid Task task, Errors errors, Authentication authentication) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        User user = (User) authentication.getPrincipal();
        task.setUser(user);
        return taskService.add(task);
    }

    @PutMapping("")
    public void editTask(@RequestBody @Valid Task task, Errors errors) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        int updated = taskService.edit(task);
        if (updated < 1) {
            throw new UpdateFailedException("Could not update task");
        }
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
