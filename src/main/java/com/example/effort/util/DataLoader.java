package com.example.effort.util;

import com.example.effort.review.Review;
import com.example.effort.review.ReviewService;
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
    private final TaskService taskService;
    private final ReviewService reviewService;

    public DataLoader(TaskService taskService, ReviewService reviewService) {
        this.taskService = taskService;
        this.reviewService = reviewService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addSampleTasks();
        addSampleReviews();
    }

    private void addSampleTasks() {
        List<Task> tasks = new ArrayList<>();
        Collections.addAll(tasks,
                new Task(LocalDate.now(), "write a report"),
                new Task(LocalDate.now(), "clean dog")
        );
        taskService.insertAll(tasks);
    }

    private void addSampleReviews() {
        List<Review> reviews = new ArrayList<>();
        Collections.addAll(reviews,
                new Review("Not a bad day after all!", LocalDate.now().minusDays(1L), 3),
                new Review("Had a blast today.", LocalDate.now(), 4)
        );
        reviewService.addAll(reviews);
    }
}
