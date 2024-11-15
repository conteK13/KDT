package com.kosmo.book.repository;

import com.kosmo.book.dto.BookReviewDTO;
import com.kosmo.book.entity.Book;
import com.kosmo.book.entity.Member;
import com.kosmo.book.entity.Review;
import com.kosmo.book.service.BookService;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@SpringBootTest
// @DataJpaTest
@Transactional
@Rollback(value = false)
@ActiveProfiles(value= "test")
// test/resources/application-test.yml 설정을 활성화 하겠다라는 의미
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// JUnit은 메서드 순서가 정해지지 않아서테스트 하고자 한다면
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)을 붙이고
// Order(번호)를 메서드 앞에 붙인다.
class ReviewRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookService bookService;

    private Member member1;
    private Member member2;

    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp(){
        // 회원 생성 및 저장
        member1 =Member.builder().name("홍길동").build();
        member2 =Member.builder().name("김철수").build();
        member1 = memberRepository.save(member1);
        member2 = memberRepository.save(member2);


        // 도서 생성 및 저장
        book1=bookRepository.save(Book.builder().title("Java Programming")
                .price(20000)
                .publish("대림")
                .bookImage("1.jpg")
                .build());

        book2=bookRepository.save(Book.builder().title("Spring Programming")
                .price(30000)
                .publish("에이스")
                .bookImage("2.jpg")
                .build());
        System.out.println("*** 북 생성 완료 ********************************");
    }


    @Test
    @Order(1) // 테스트 순서 1번
    public void 회원이_도서에_리뷰를_작성하고_조회(){
        // 리뷰 생성 및 저장
        // member1, book1에 대한 리뷰하기
        reviewRepository.save(Review.builder()
                .content("정말 좋은 프로그래밍 책이에요")
                .rating(5)
                .book(book1)
                .member(member1)
                .build());

        // member1, book2에 대해 리뷰하기
        reviewRepository.save(Review.builder()
                .content("다소 어렵게 느껴지네요")
                .rating(4)
                .book(book2)
                .member(member1)
                .build());

        //member2, book1에 대해 리뷰하기
        reviewRepository.save(Review.builder()
                .content("너무 쉽게 쓴 책...")
                .rating(3)
                .book(book1)
                .member(member2)
                .build());

        //member2, book1에 대해 리뷰하기
        reviewRepository.save(Review.builder()
                .content("그냥 그저 그래요")
                .rating(2)
                .book(book1)
                .member(member2)
                .build());


        // book1은 3개 리뷰 달림, book2는 1개 리뷰 달림
        // member1은 2개 씀, member2는 2개 씀
        // 저장된 리뷰 조회 (member1의 리뷰 조회)
        List<Review> member1Reviews = reviewRepository.findByMemberId(member1.getId());

        System.out.println("member1Reviews : "+member1Reviews);
        assertThat(member1Reviews.size()).isEqualTo(2);
        assertThat(member1Reviews.get(0).getMember().getName()).isEqualTo("홍길동");


//        //도서별 리뷰 확인(book1에 대한 리뷰)
//        List<Review> book1Reviews = reviewRepository.findByBookId(book1.getId());
//        System.out.println("book1Reviews : "+book1Reviews);
//        assertThat(book1Reviews.size()).isEqualTo(3);
//        assertThat(book1Reviews.get(0).getBook().getTitle()).isEqualTo("Java Programming");
    }//----------------------------------------


    @Order(2)
    @Test
    public void 특정도서_리뷰목록_조회(){
        List<Book> list = bookRepository.findByTitle("Java Programming");
        if (list != null) {
            Long bookId = list.get(0).getId(); //테스트할 도서 번호
            List<BookReviewDTO> reviews = bookService.getReviewsForBook(bookId);
            System.out.println("*".repeat(30));
            reviews.stream().forEach(System.out::println);
            System.out.println("*".repeat(30));
        }
    }
}