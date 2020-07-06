package com.example.effort.category;

import com.example.effort.user.User;
import org.springframework.security.core.Authentication;
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
    public Category add(@RequestBody Category category, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        category.setUser(user);
        return categoryService.add(category);
    }

    @PutMapping("")
    public void edit(@RequestBody Category category) throws Exception {
        int updated = categoryService.edit(category);
        if (updated < 1)
            throw new Exception("Could not update category");
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws Exception {
        int deleted = categoryService.deleteById(id);
        if (deleted < 1)
            throw new Exception("Could not delete category");
    }

}
