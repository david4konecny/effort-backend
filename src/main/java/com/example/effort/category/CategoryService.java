package com.example.effort.category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();
    List<Category> insertAll(List<Category> categories);
    Category add(Category category);
    int edit(Category category);
    int deleteById(Long categoryId);

}
