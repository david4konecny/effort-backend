package com.example.effort.review;

import com.example.effort.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public List<Review> getReviews(@RequestParam(required = false) String date) {
        if (date == null)
            return reviewService.getAll();
        else
            return reviewService.getReviewsByDate(date);
    }

    @PostMapping("")
    public Review insert(@RequestBody Review review, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        review.setUser(user);
        review.setId(null);
        return reviewService.insert(review);
    }

    @PutMapping("")
    public void edit(@RequestBody Review review) throws Exception {
        int updated = reviewService.edit(review);
        if (updated < 1)
            throw new Exception("Could not update review");
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws Exception {
        int deleted = reviewService.deleteById(id);
        if (deleted < 1)
            throw new Exception("Could not delete review");
    }

}
