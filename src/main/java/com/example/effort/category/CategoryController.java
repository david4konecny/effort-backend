package com.example.effort.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping("")
    public Category add(@RequestBody Category category) {
        return categoryService.add(category);
    }

    @PutMapping("")
    public Category edit(@RequestBody Category category) {
        return categoryService.edit(category);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

}
