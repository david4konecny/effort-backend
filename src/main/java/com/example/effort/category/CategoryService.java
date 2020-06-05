package com.example.effort.category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();
    void insertAll(List<Category> categories);
    Category add(Category category);
    Category edit(Category category);
    void deleteById(Long id);

}
