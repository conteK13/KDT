package com.kosmo.book.dto;

import com.kosmo.book.entity.Book;
import com.kosmo.book.entity.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookReviewDTO {

    private String title;
    private String content;
    private int rating;
    private String name;
//    private String id;
//
//    // id 필드를 제외한 기존 생성자 수동 추가
//    public BookReviewDTO(String title, String content, int rating, String name) {
//        this.title = title;
//        this.content = content;
//        this.rating = rating;
//        this.name = name;
//        // id 필드는 기본값(null)로 초기화됨
//    }

}
