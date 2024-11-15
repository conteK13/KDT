package com.kosmo.book.service;

import com.kosmo.book.dto.ReviewDTO;
import com.kosmo.book.entity.Book;
import com.kosmo.book.entity.Member;
import com.kosmo.book.entity.Review;
import com.kosmo.book.repository.BookRepository;
import com.kosmo.book.repository.MemberRepository;
import com.kosmo.book.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Override
    public Review saveReview(ReviewDTO reviewDTO) {
        log.info("saveReview 시작");
        log.info("reviewDTO======================={}",reviewDTO);
        Book book = bookRepository.findById(reviewDTO.getBookId()).orElseThrow(() -> new IllegalArgumentException("Book을 못찾아"));
        Member member = memberRepository.findById(reviewDTO.getMemberId()).orElseThrow(() -> new IllegalArgumentException("member를 못찾아"));


        Review createEntity = Review.builder()
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .book(book)
                .member(member)
                .build();
        Review entity = reviewRepository.save(createEntity);
        return entity;
    }
}
