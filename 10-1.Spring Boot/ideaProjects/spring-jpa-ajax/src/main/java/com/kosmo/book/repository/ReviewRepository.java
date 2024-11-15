package com.kosmo.book.repository;

import com.kosmo.book.dto.BookReviewDTO;
import com.kosmo.book.dto.ReviewDTO;
import com.kosmo.book.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 회원의 리뷰 목록 가져오기
    List<Review> findByMemberId(Long memberId);

    // 특정 도서에 대한 리뷰 목록 가져오기
    List<Review> findByBookId(Long bookId);

    // 특정 도서에 대한 리뷰 목록 가져오기
    // [1] 메서드 이름 기반 쿼리 가져오기
    // [2] JPQL 이용해서 가져오기
    // [3] Named Query 이용해서 가져오기

    // 쿼리문을 작성한다 ===> JPQL

    @Query("SELECT new com.kosmo.book.dto.BookReviewDTO(b.title, r.content, r.rating, m.name) " +
            "FROM Review r " +
            "JOIN r.book b " +
            "JOIN r.member m " +
            "WHERE b.id = :bookId")
    List<BookReviewDTO> findByReviewsByBookId(@Param("bookId") Long bookId);


    @Query("SELECT new com.kosmo.book.dto.ReviewDTO(r.id, r.content, r.rating, b.id, m.id) " +
            "FROM Review r " +
            "JOIN r.book b " +
            "JOIN r.member m " +
            "WHERE b.id = :bookId "+
            "ORDER BY r.id DESC")
    List<ReviewDTO> findReviewsByBookId(@Param("bookId") Long bookId);

}
