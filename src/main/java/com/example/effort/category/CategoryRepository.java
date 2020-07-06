package com.example.effort.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.user.id = ?#{ principal?.id }")
    List<Category> findAllForUser();

    @Query("select count(c) = 1 from Category c where c.id = ?1 and c.user.id = ?#{ principal?.id }")
    boolean isUserOwnerOfCategory(Long categoryId);

    @Modifying
    @Transactional
    @Query("update Category c set c.name = ?#{ [0].name }, c.color = ?#{ [0].color } where c.id = ?#{ [0].id } and c.user.id = ?#{ principal?.id }")
    int editCategory(Category category);

    @Modifying
    @Transactional
    @Query("delete from Category c where c.id = ?1 and c.user.id = ?#{ principal?.id }")
    int deleteCategory(Long id);

    @Modifying
    @Transactional
    @Query("delete from Category c where c.user.id = ?#{ principal?.id }")
    void deleteCategories();

}
