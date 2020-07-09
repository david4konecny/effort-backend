package com.example.effort.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAll();
    List<Review> getReviewsByDate(String date);
    Review insert(Review review);
    List<Review> insertAll(List<Review> reviews);
    int edit(Review review);
    int deleteById(Long id);
    void addAll(List<Review> reviews);

}
