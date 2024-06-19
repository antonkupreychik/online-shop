package com.kupreychik.productservice.service;

import com.kupreychik.productservice.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<Review> findAllReviewsByProductId(Long productId, Pageable pageable);

    Review addReview(Review review);

    boolean deleteReview(Long reviewId);
}
