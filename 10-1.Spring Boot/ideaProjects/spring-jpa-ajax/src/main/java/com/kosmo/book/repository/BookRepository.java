package com.kosmo.book.repository;

import com.kosmo.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spring Data JPA : JPA Repository 인터페이스를 상속받는 인터페이스
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByPublish(String publish);
    List<Book> findByPriceLessThan(int price);              // <price
    List<Book> findByPriceGreaterThanEqual(int price);      // >=price
    List<Book> findByPublishOrTitle(String publish, String title);

    List<Book> findByTitleLike(String title);   //like 검색

}
