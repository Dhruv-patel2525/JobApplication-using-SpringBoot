package org.learn.jobapp.Review.impl;

import org.learn.jobapp.Review.Review;
import org.learn.jobapp.Review.ReviewRepository;
import org.learn.jobapp.Review.ReviewService;
import org.learn.jobapp.company.Company;
import org.learn.jobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company!=null)
        {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId,Review updatedReview) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null)
        {
            updatedReview.setCompany(company);
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }
    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company company = companyService.getCompanyById(companyId);
        if( company!= null && reviewRepository.existsById(reviewId))
        {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            company.getReviews().remove(review);
            companyService.updateCompany(companyId,company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
