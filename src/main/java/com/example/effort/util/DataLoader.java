package com.example.effort.util;

import com.example.effort.category.Category;
import com.example.effort.category.CategoryService;
import com.example.effort.review.Review;
import com.example.effort.review.ReviewService;
import com.example.effort.task.Task;
import com.example.effort.task.TaskService;
import com.example.effort.time.CurrentTimeEntry;
import com.example.effort.time.FinishedTimeEntry;
import com.example.effort.time.TimeService;
import com.example.effort.time.TimeEntry;
import com.example.effort.user.User;
import com.example.effort.user.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private final UserService userService;
    private final TaskService taskService;
    private final ReviewService reviewService;
    private final CategoryService categoryService;
    private final TimeService timeService;
    private final List<Category> categories = new ArrayList<>();
    private final User u1 = new User("joe", "testtest");
    private final User u2 = new User("frank", "testtest");

    public DataLoader(UserService userService, TaskService taskService, ReviewService reviewService, CategoryService categoryService, TimeService timeService) {
        this.userService = userService;
        this.taskService = taskService;
        this.reviewService = reviewService;
        this.categoryService = categoryService;
        this.timeService = timeService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addSampleUsers();
        addSampleTasks();
        addSampleReviews();
        addSampleCategories();
        addSampleTimeEntries();
    }

    private void addSampleUsers() {
        userService.insert(u1);
        userService.insert(u2);
    }

    private void addSampleTasks() {
        List<Task> tasks = new ArrayList<>();
        Collections.addAll(tasks,
                new Task(u1, LocalDate.now().minusDays(1L), "watch a movie"),
                new Task(u1, LocalDate.now(), "write a report"),
                new Task(u2, LocalDate.now(), "clean dog")
        );
        taskService.insertAll(tasks);
    }

    private void addSampleReviews() {
        List<Review> reviews = new ArrayList<>();
        Collections.addAll(reviews,
                new Review(u1, "Not a bad day after all!", LocalDate.now().minusDays(1L), 3),
                new Review(u2, "Had a blast today.", LocalDate.now(), 4)
        );
        reviewService.addAll(reviews);
    }

    private void addSampleCategories() {
        Collections.addAll(categories,
                new Category(u1, "programming", "#00ff00"),
                new Category(u1, "writing", "#ff0000"),
                new Category(u2, "chess", "#ff0000"),
                new Category(u2, "writing", "#ff0000")
        );
        categoryService.insertAll(categories);
    }

    private void addSampleTimeEntries() {
        // CurrentTimeEntry c = new CurrentTimeEntry(u1, LocalDate.now(), categories.get(0), LocalTime.now().minusMinutes(30L).toSecondOfDay(), LocalTime.now().toSecondOfDay());
        FinishedTimeEntry f = new FinishedTimeEntry(u1, LocalDate.now(), categories.get(1), LocalTime.of(6, 0).toSecondOfDay(), LocalTime.of(6,30).toSecondOfDay());
        // timeService.insertCurrent(c);
        timeService.insertFinished(f);
    }

}
