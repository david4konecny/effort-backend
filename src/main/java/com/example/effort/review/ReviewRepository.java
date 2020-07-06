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

    @Query("select avg(r.rating) from Review r where r.user.id = ?#{ principal?.id } and r.date >= ?1 and r.date <= ?2")
    Double getAverageRatingForPeriod(LocalDate startDate, LocalDate endDate);

    @Modifying
    @Transactional
    @Query("update Review r set r.description = ?#{ [0].description }, r.date = ?#{ [0].date }, r.rating = ?#{ [0].rating } where r.id = ?#{ [0].id } and r.user.id = ?#{ principal?.id }")
    int editReview(Review review);

    @Modifying
    @Transactional
    @Query("delete from Review r where r.id = ?#{ [0] } and r.user.id = ?#{ principal?.id }")
    int deleteReview(Long id);

    @Modifying
    @Transactional
    @Query("delete from Review r where r.user.id = ?#{ principal?.id }")
    void deleteReviews();

}
