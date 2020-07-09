package com.example.effort.category;

import com.example.effort.user.User;
import com.example.effort.util.exceptions.DataNotValidException;
import com.example.effort.util.exceptions.UpdateFailedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Category add(@RequestBody @Valid Category category, Errors errors, Authentication authentication) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        User user = (User) authentication.getPrincipal();
        category.setUser(user);
        return categoryService.add(category);
    }

    @PutMapping("")
    public void edit(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        int updated = categoryService.edit(category);
        if (updated < 1)
            throw new UpdateFailedException("Could not update category");
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        int deleted = categoryService.deleteById(id);
        if (deleted < 1)
            throw new UpdateFailedException("Could not delete category");
    }

}
