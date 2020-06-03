package com.example.effort.task;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    public Task add(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task edit(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void insertAll(List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<DateAndTasksView> getTaskSummariesByDateForPeriod(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<DateAndTasksView> fromDb = taskRepository.getTaskSummariesByDate(start, end);
        return fillMissingDates(fromDb, start, end);
    }

    private List<DateAndTasksView> fillMissingDates(List<DateAndTasksView> data, LocalDate start, LocalDate end) {
        long periodLength = end.toEpochDay() - start.toEpochDay();
        Map<LocalDate, Long[]> map = new TreeMap<>();
        data.forEach(item -> map.put(item.getDate(), new Long[] {item.getCount(), item.getFinished()}));

        for (int i = 0; i <= periodLength; i++) {
            LocalDate date = start.plusDays(i);
            if (!map.containsKey(date)) {
                map.put(date, new Long[] {0L, 0L});
            }
        }

        List<DateAndTasksView> res = new ArrayList<>();
        map.forEach((date, values) -> res.add(new DateAndTasksView(date, values[0], values[1])));
        return res;
    }
}
