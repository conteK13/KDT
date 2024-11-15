package com.kosmo.book.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


// @SpringBootTest             // @Data.JpaTest==> Spring에서 JPA 관련  테스트 설정만 로드
@Transactional              // 트랜젝션 시작 후 > 테스트 진행 > rollback처리(테스트시)
@Rollback(value = false)    // 롤백하지 않도록 설정해서 DB데이터를 확인해보자
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void print(){
        System.out.println("*".repeat(50));
    }

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    public void testSave(){
//        Book book = new Book(null, "채식주의자", "한강출판사", 20000, "b.jpg");
//        Book createBook = bookRepository.save(book);    // ==>insert
//        Assertions.assertThat(book.getTitle()).isEqualTo(createBook.getTitle());
//
//        List<Book> list = bookRepository.findAll();     // findAll() => select * from 테이블
//        list.stream().forEach(System.out::println);
//    }

    @Test
    public void testFindById(){
        Optional<Book> opt = bookRepository.findById(4L);
        if(opt.isPresent()){
            Book findBook = opt.get();
            System.out.println("findBook : "+findBook);
        }else{
            System.out.println("4번 도서는 없어요.");
        }
        if (opt.isPresent()){
            Assertions.assertThat(opt.get().getTitle()).isEqualTo("JSP 응용");
        }
    }//-----------------------

    @Test
    public void testDeleteById(){
        Optional<Book> opt = bookRepository.findById(1L);
        if (opt.isPresent()){
            bookRepository.deleteById(1L);      //pk로 삭제
                    // deleteAll() =====> 모든 레코드 삭제
        }else{
            System.out.println("1번 도서는 없어요");
        }
        Long cnt = bookRepository.count();      // 총 레코드 수 가져오기
        System.out.println("전체 레코드 수 : " + cnt);
        Assertions.assertThat(cnt).isEqualTo(3L);
    }//---------------

    @Test
    public void testUpdateBook(){
        Book existBook = bookRepository.findById(3L)
                .orElseThrow(()-> new IllegalArgumentException("3번 도서 없어요"));
        existBook.setTitle("JAVA 심화");
        existBook.setPublish("생능 출판사");
        existBook.setPrice(10000);
        existBook.setBookImage("java.jpg");

        Book updateBook = bookRepository.save(existBook);
        Assertions.assertThat(updateBook.getTitle()).isEqualTo("JAVA 심화");
        Assertions.assertThat(updateBook.getPrice()).isEqualTo(10000);
        System.out.println("수정 처리된 도서 정보 : "+updateBook);
    }//--------------------

    @Test
    public void testFindTitle(){
        List<Book> list = bookRepository.findByTitle("JSP 응용");
        list.stream().forEach(System.out::println);
    }//-------------------------

    @Test
    public void testFind(){
        List<Book> list = bookRepository.findByPublish("골든 래빗");
        list.stream().forEach(System.out::println);
        System.out.println("-----2만 미만 도서----------------");
        list=bookRepository.findByPriceLessThan(20000);
        list.stream().forEach(System.out::println);
        System.out.println("-----2만 이상 도서----------------");
        list=bookRepository.findByPriceGreaterThanEqual(20000);
        list.stream().forEach(System.out::println);
        System.out.println("---------------------");
        list=bookRepository.findByPublishOrTitle("생능 출판사", "JAVA 심화");
        list.stream().forEach(System.out::println);
    }//--------------------

    @Test
    public void testFindLike(){
        List<Book> list = bookRepository.findByTitleLike("%기초");
        list.stream().forEach(System.out::println);
        System.out.println("--------------------------------");
    }//------------------------------------------

    @Test
    public void testBuilder(){
        Book entity = Book.builder()
                .title("빌더 디자인 패턴")
                .publish("한빛 출판사")
                .price(30000)
                .bookImage("book7.jpg")
                .build();
        System.out.println("entity : "+entity);
        bookRepository.save(entity);
    }

    @Test
    public void testConvert2(){
        BookDTO dto=new BookDTO(null,"DTO테스트","위키북스",5000,"w.jpg");
        Book book=objectMapper.convertValue(dto, Book.class);
        System.out.println("book====="+book);
    }

    @Test
    public void testBookPaging(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        // Sort sort2 = Sort.by("title").ascending();
        Pageable pageable = PageRequest.of(0,3, sort);
        Page<Book> page = bookRepository.findAll(pageable);
        System.out.println("page================= "+ page);
        // 해당 페이지의 게시글 목록 List<Book> getContent()
        System.out.println("content============== "+page.getContent());
        //총 게시글 수 가져오기 : long getTotalElements()
        System.out.println("총 게시물 수=========== "+page.getTotalElements());
        System.out.println("총 페이지 수=========== "+page.getTotalPages());
        System.out.println("한 페이지 당 목록 개수=== "+pageable.getPageSize());
        System.out.println("pageable============= "+page.getPageable());
        System.out.println("현제 페이지 번호======== "+page.getPageable().getPageNumber());
        // 현재 페이지 번호 : 0 => 1페이지에 0을 반환
    }
}// class end /////////////////////////////////