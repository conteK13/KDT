package com.kosmo.book.repository;

import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spring Data JPA : JPA Repository 인터페이스를 상속받는 인터페이스
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // List<Book> : findAll() : 기본적으로 제공되는 메서드
    // Page<Book> : findAll(Pageable) : 페이징 기능을 포함한 모든 목록 가져오기


    List<Book> findByTitle(String title);
    List<Book> findByPublish(String publish);
    List<Book> findByPriceLessThan(int price);              // <price
    List<Book> findByPriceGreaterThanEqual(int price);      // >=price
    List<Book> findByPublishOrTitle(String publish, String title);

    List<Book> findByTitleLike(String title);   //like 검색, 대 소문자 구분함
    List<Book> findByTitleContainingIgnoreCase(String title);   //like 검색. 대소문자 구분하지 않음.

    // like 검색. 대소문자를 구분하지 않고 검색+Paging처리 까지 포함
    Page<Book> findByTitleLikeIgnoreCase(String s, Pageable pageable);

}
