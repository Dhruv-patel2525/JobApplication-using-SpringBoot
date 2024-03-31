package org.learn.reviewms.review.impl;

import org.learn.reviewms.review.Review;
import org.learn.reviewms.review.ReviewRepository;
import org.learn.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
//    CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId!=null && review!=null)
        {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById( Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        return review;


    }

    @Override
    public boolean updateReview(Long reviewId,Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null)
        {
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setTitle(updatedReview.getTitle());
            review.setCompanyId(updatedReview.getCompanyId() );

            reviewRepository.save(review);
            return true;
        }
        return false;
    }
    @Override
    public boolean deleteReview( Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null)
        {
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
