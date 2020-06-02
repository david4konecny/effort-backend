package com.example.effort.review;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<Review> getReviews(@RequestParam(required = false) String date) {
        if (date == null)
            return reviewService.getAll();
        else
            return reviewService.getReviewsByDate(date);
    }

    @PostMapping("/review")
    public Review insert(@RequestBody Review review) {
        return reviewService.insert(review);
    }

    @PutMapping("/review")
    public Review edit(@RequestBody Review review) {
        return reviewService.edit(review);
    }

    @DeleteMapping("/review/{id}")
    public void deleteById(@PathVariable Long id) {
        reviewService.deleteById(id);
    }

}
