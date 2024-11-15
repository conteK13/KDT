package com.kosmo.book.dto;



import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewDTO {

    private Long id;            // Review ID
    private String content;     // 리뷰 내용
    private int rating;         // 평가점수
    private Long bookId;        // 책 ID
    private Long memberId;      // MemberID
}
