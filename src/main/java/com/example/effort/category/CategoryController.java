package com.example.effort.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping("/category")
    public Category add(@RequestBody Category category) {
        return categoryService.add(category);
    }

    @PutMapping("/category")
    public Category edit(@RequestBody Category category) {
        return categoryService.edit(category);
    }

    @DeleteMapping("/category/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

}
