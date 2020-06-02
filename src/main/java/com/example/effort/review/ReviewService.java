package com.example.effort.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAll();
    List<Review> getReviewsByDate(String date);
    Review insert(Review review);
    Review edit(Review review);
    void deleteById(Long id);
    void addAll(List<Review> reviews);

}
