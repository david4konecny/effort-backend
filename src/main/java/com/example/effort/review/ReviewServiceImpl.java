package com.example.effort.review;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return reviewRepository.findByDate(d);
    }

    @Override
    public Review insert(Review review) {
        review.setId(null);
        return reviewRepository.save(review);
    }

    @Override
    public int edit(Review review) {
        return reviewRepository.editReview(review);
    }

    @Override
    public int deleteById(Long id) {
        return reviewRepository.deleteReview(id);
    }

    @Override
    public void addAll(List<Review> reviews) {
        reviewRepository.saveAll(reviews);
    }

}
