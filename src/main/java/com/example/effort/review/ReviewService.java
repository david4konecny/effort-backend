package com.example.effort.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAll();
    List<Review> getReviewsByDate(String date);
    void addAll(List<Review> reviews);

}
