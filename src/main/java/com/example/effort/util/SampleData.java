package com.example.effort.util;

import com.example.effort.category.Category;
import com.example.effort.category.CategoryService;
import com.example.effort.task.Task;
import com.example.effort.task.TaskService;
import com.example.effort.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SampleData {
    private final TaskService taskService;
    private final CategoryService categoryService;

    public SampleData(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    public void addDefaultCategory(User user) {
        Category category = new Category(user, "default", "#3700b3");
        this.categoryService.add(category);
    }

    public void addSampleData(User user) {
        categoryService.insertAll(getSampleCategories(user));
        taskService.insertAll(getSampleTasks(user));
    }

    private List<Category> getSampleCategories(User user) {
        List<Category> categories = new ArrayList<>();
        Collections.addAll(categories,
                new Category(user, "programming", "#00ff00"),
                new Category(user, "writing", "#ff0000"),
                new Category(user, "research", "#ff0000"),
                new Category(user, "german", "#ff0000")
        );
        return categories;
    }

    private List<Task> getSampleTasks(User user) {
        List<Task> tasks = new ArrayList<>();
        Collections.addAll(tasks,
                new Task(user, LocalDate.now().minusDays(1L), "watch a movie", 100.0),
                new Task(user, LocalDate.now(), "write a report", 100.0),
                new Task(user, LocalDate.now(), "clean dog", 200.0)
        );
        return tasks;
    }

}
