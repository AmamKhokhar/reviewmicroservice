package com.learner.reviewms.review.serviceimpl;



import com.learner.reviewms.review.Review;
import com.learner.reviewms.review.ReviewRepository;
import com.learner.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null && review != null) {
            review.setCompanyid(companyId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review!=null){
            review.setDescription(updatedReview.getDescription());
            review.setTitle(updatedReview.getTitle());
            review.setCompanyid(updatedReview.getCompanyid());
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            reviewRepository.delete(review);
            return true;
        }
        else {
            return false;
        }
    }
}
