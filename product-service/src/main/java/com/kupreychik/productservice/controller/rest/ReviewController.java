package com.kupreychik.productservice.controller.rest;

import com.kupreychik.productservice.model.entity.Review;
import com.kupreychik.productservice.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/products/{productId}/reviews")
@RequiredArgsConstructor
@Tag(name = "Review", description = "Operations related to reviews in the Product Service")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "Get all reviews for a product", description = "Returns a paginated list of reviews for a specific product")
    public ResponseEntity<Page<Review>> getAllReviewsForProduct(
            @Parameter(description = "The ID of the product to get reviews for", required = true) @PathVariable Long productId,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(reviewService.findAllReviewsByProductId(productId, pageable));
    }

    @PostMapping
    @Operation(summary = "Add a review to a product", description = "Creates a new review for a product and returns the created review")
    @ApiResponse(responseCode = "201", description = "Review created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Review.class)))
    public ResponseEntity<Review> addReviewToProduct(
            @Parameter(description = "The ID of the product to add a review to", required = true) @PathVariable Long productId,
            @Parameter(description = "Review data to be added", required = true) @RequestBody Review review) {
        review.setProductId(productId);
        Review createdReview = reviewService.addReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "Delete a review", description = "Deletes a review by its ID and returns a status")
    @ApiResponse(responseCode = "200", description = "Review deleted")
    @ApiResponse(responseCode = "404", description = "Review not found")
    public ResponseEntity<Void> deleteReview(
            @Parameter(description = "The ID of the review to delete", required = true) @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(reviewId);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
