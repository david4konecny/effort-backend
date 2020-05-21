package com.example.effort.category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();
    void insertAll(List<Category> categories);

}
