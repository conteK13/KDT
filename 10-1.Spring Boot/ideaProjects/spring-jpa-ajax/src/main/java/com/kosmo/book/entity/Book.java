package com.kosmo.book.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder        // 매개변수를 받는 생성자가 있어야 적용할 수 있다.
//isbn, title, publish, price, book_image
@Entity
@Table(name="book")
@SequenceGenerator(name="BOOK_SEQ_GEN",//시퀀스 제너레이터명
        sequenceName = "BOOK_SEQ", //시퀀스명
        initialValue = 1,//시작값
        allocationSize = 1 //시퀀스 증가 단위
)
public class Book {
    @Id //PK 에 붙인다
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BOOK_SEQ_GEN")
    @Column(name="isbn")
    private Long id;

    @Column(name="title", nullable = false)
    private String title;
    private String publish;
    private int price;
    @Column(name="book_image")
    private String bookImage;
}
