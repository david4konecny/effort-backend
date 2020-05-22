package com.example.effort.task;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return taskRepository.findByDate(d);
    }

    @Override
    public void insertAll(List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
