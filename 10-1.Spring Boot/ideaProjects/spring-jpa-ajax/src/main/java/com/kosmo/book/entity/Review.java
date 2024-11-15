package com.kosmo.book.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; //리뷰 내용

    private int rating; //평가점수
//    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)  // 연관관계는 가급적 지연로딩으로 설정하자.
                                        // FetchType.EAGER (즉시로딩- 디폴트)
    @JoinColumn(name="book_id") //FK명을 @JoinColumn에 기술한다.
    private Book book;

    /*
    * FetchType.LAZY(지연로딩)
    * 이 설정은 관련된 엔티티를 실제로 사용할 때까지
    * DB에서 가져오지 않겠다는 의미.
    * */
//    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")   //FK 명을 member_id로 설정
    private Member member;

}
