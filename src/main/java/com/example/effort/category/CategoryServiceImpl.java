package com.example.effort.category;

import com.example.effort.time.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final TimeRepository timeRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, TimeRepository timeRepository) {
        this.categoryRepository = categoryRepository;
        this.timeRepository = timeRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAllForUser();
    }

    @Override
    public void insertAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category edit(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        timeRepository.deleteEntriesForCategory(id);
        categoryRepository.deleteById(id);
    }
}
