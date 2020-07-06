package com.example.effort.category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();
    void insertAll(List<Category> categories);
    Category add(Category category);
    int edit(Category category);
    int deleteById(Long categoryId);

}
