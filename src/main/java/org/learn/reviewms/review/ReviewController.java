package org.learn.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId)
    {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId ,@RequestBody Review review)
    {
        boolean created = reviewService.addReview(companyId,review);
        if(created) {
            return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId)
    {
        Review review = reviewService.getReviewById(reviewId);
        if(review!=null)
        {
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Review review)
    {
        boolean isUpdated = reviewService.updateReview(reviewId,review);
        if(isUpdated)
        {
            return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId)
    {
        boolean isDeleted= reviewService.deleteReview(reviewId);
        if(isDeleted)
        {
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
    }
}
