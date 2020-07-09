package com.example.effort.review;

import com.example.effort.user.User;
import com.example.effort.util.exceptions.DataNotValidException;
import com.example.effort.util.exceptions.UpdateFailedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Review insert(@RequestBody @Valid Review review, Errors errors, Authentication authentication) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        User user = (User) authentication.getPrincipal();
        review.setUser(user);
        review.setId(null);
        return reviewService.insert(review);
    }

    @PutMapping("")
    public void edit(@RequestBody @Valid Review review, Errors errors) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        int updated = reviewService.edit(review);
        if (updated < 1)
            throw new UpdateFailedException("Could not update review");
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        int deleted = reviewService.deleteById(id);
        if (deleted < 1)
            throw new UpdateFailedException("Could not delete review");
    }

}
