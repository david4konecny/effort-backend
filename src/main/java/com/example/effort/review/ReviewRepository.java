package com.example.effort.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.date = ?1 and r.user.id = ?#{ principal?.id }")
    List<Review> findByDate(LocalDate date);

    @Modifying
    @Transactional
    @Query("delete from Review r where r.user.id = ?#{ principal?.id }")
    void deleteReviews();

}
