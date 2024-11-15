package com.kosmo.book.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder        // 매개변수를 받는 생성자가 있어야 적용할 수 있다.
//isbn, title, publish, price, book_image
@Entity
@Table(name="book")
//@SequenceGenerator(name="BOOK_SEQ_GEN",//시퀀스 제너레이터명
//        sequenceName = "BOOK_SEQ", //시퀀스명
//        initialValue = 1,//시작값
//        allocationSize = 1 //시퀀스 증가 단위
//)
public class Book {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //mysql auto_increment
    @Column(name="isbn")
    private Long id;


    @Column(name="title", nullable = false)
    private String title;
    private String publish;
    private int price;
    @Column(name="book_image")
    private String bookImage;


    // 양방향 관계를 맺을 때는 mappedBy 속성을 설정
    // mappedBy는 FK가 아닌 쪽에서 설정(누가 주인인지 설정)
    // mappedBy가 지정된 족(Book)이 주인이 아니라는 이야기
    // Review가 Book과의 관계를 주도하고, Member와의 관계도 주도한다.
//    @OneToMany(mappedBy = "book") // book은 필드명. Review 엔티티에서 Book book 일나 필드를 가짐.
//    private List<Review> reviews = new ArrayList<>();

}
