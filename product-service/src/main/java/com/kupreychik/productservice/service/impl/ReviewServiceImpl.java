package com.kupreychik.productservice.service.impl;

import com.kupreychik.productservice.model.entity.Review;
import com.kupreychik.productservice.repository.ReviewRepository;
import com.kupreychik.productservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> findAllReviewsByProductId(Long productId, Pageable pageable) {
        return reviewRepository.findAllByProductId(productId, pageable);
    }

    @Override
    public Review addReview(Review review) {
        return null;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        return false;
    }
}
