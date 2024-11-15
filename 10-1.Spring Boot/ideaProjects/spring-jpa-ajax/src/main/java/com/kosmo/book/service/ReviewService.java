package com.kosmo.book.service;


import com.kosmo.book.dto.ReviewDTO;
import com.kosmo.book.entity.Review;

public interface ReviewService {
    Review saveReview(ReviewDTO reviewDTO);
}
