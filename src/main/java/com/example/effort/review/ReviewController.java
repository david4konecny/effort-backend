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
        return reviewService.insert(review);
    }

    @PutMapping("")
    public Review edit(@RequestBody Review review) {
        return reviewService.edit(review);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reviewService.deleteById(id);
    }

}
