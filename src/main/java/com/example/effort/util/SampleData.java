package com.example.effort.util;

import com.example.effort.category.Category;
import com.example.effort.category.CategoryService;
import com.example.effort.review.Review;
import com.example.effort.review.ReviewService;
import com.example.effort.task.Task;
import com.example.effort.task.TaskService;
import com.example.effort.time.FinishedTimeEntry;
import com.example.effort.time.TimeService;
import com.example.effort.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class SampleData {
    private final CategoryService categoryService;
    private final ReviewService reviewService;
    private final TaskService taskService;
    private final TimeService timeService;
    private final Random rand = new Random();
    private static final String[][] taskNames = { {"write a report", "buy groceries", "morning run"}, {"wash the dog", "pay the bills", "call a grandma"} };
    private static final String[] sampleReviews = { "This day was horrible.", "An OK day.", "This was fun.", "I got soo many things done!" };

    public SampleData(CategoryService categoryService, ReviewService reviewService, TaskService taskService, TimeService timeService) {
        this.categoryService = categoryService;
        this.reviewService = reviewService;
        this.taskService = taskService;
        this.timeService = timeService;
    }

    public void addDefaultCategory(User user) {
        Category category = new Category(user, "default", "#3700b3");
        this.categoryService.add(category);
    }

    public void addSampleData(User user) {
        List<Category> categories = categoryService.insertAll(getSampleCategories(user));
        addSampleTasks(user);
        addSampleReviews(user);
        addSampleTimeEntries(user, categories);
    }

    private List<Category> getSampleCategories(User user) {
        List<Category> categories = new ArrayList<>();
        Collections.addAll(categories,
                new Category(user, "programming", "#3700b3"),
                new Category(user, "writing", "#00897b"),
                new Category(user, "learning", "#b00020")
        );
        return categories;
    }

    private void addSampleTasks(User user) {
        List<Task> tasks = new ArrayList<>();
        addPastTasks(user, tasks);
        addFutureTasks(user, tasks);
        taskService.insertAll(tasks);
    }

    private void addPastTasks(User user, List<Task> tasks) {
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 20; i++) {
            date = date.minusDays(1 + rand.nextInt(2));
            int numOfTasks = rand.nextInt(2) + 1;
            for (int j = 0; j < numOfTasks; j++) {
                tasks.add(new Task(user, date, taskNames[j][rand.nextInt(3)], rand.nextBoolean(), 100_000.0 + (10_000.0 * j)));
            }
        }
    }

    private void addFutureTasks(User user, List<Task> tasks) {
        LocalDate date = LocalDate.now().minusDays(1);
        for (int i = 0; i < 20; i++) {
            date = date.plusDays(1 + rand.nextInt(2));
            int numOfTasks = rand.nextInt(2) + 1;
            for (int j = 0; j < numOfTasks; j++) {
                tasks.add(new Task(user, date, taskNames[j][rand.nextInt(3)], 100_000.0 + (10_000.0 * j)));
            }
        }
    }

    private void addSampleReviews(User user) {
        List<Review> reviews = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 20; i++) {
            date = date.minusDays(1 + rand.nextInt(2));
            int j = rand.nextInt(4);
            reviews.add(new Review(user, sampleReviews[j], date, (j * 2) + 2));
        }
        reviewService.insertAll(reviews);
    }

    private void addSampleTimeEntries(User user, List<Category> categories) {
        List<FinishedTimeEntry> entries = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 20; i++) {
            date = date.minusDays(1 + rand.nextInt(2));
            int startTime = 25_200;
            int endTme;
            for (int j = 0; j < (rand.nextInt(3) + 1); j++) {
                endTme = startTime + (1_800 * rand.nextInt(7));
                entries.add(new FinishedTimeEntry(user, date, categories.get(rand.nextInt(3)), startTime, endTme));
                startTime = endTme + 3_600;
            }
        }
        timeService.insertAllFinished(entries);
    }

}
