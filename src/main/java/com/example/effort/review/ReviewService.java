package com.example.effort.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAll();
    void addAll(List<Review> reviews);

}
