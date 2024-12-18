package com.kosmo.book.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.basic.entity.BookEntity;
import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.dto.BookReviewDTO;
import com.kosmo.book.entity.Book;
import com.kosmo.book.repository.BookRepository;
import com.kosmo.book.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;
    private final ReviewRepository reviewRepository;
    //Entity와 DTO 객체를 변환할때 사용

    @Override
    public List<BookDTO> getAllBooks(String search) {   // 페이지 처리 안한 경우
        List<Book> list= null;

        if (search.isBlank()) {
            list = bookRepository.findAll();
        }else{
            //list = title로 검색한 결과 받아오는 메소드 호출
            //list= bookRepository.findByTitleLike("%"+search+"%");
            list= bookRepository.findByTitleContainingIgnoreCase(search);
        }
            // repository에서 받은 데이터는 엔티티 객체
            // ==>표현 계층에 전달 할때는 DTO로 변환해야 한다.
            List<BookDTO> arrList = list.stream().map(entity        // stream을 통해 for문 처럼 동작
                            -> new BookDTO(entity.getId(), entity.getTitle(),
                            entity.getPublish(), entity.getPrice(), entity.getBookImage()))
                    .collect(Collectors.toList());
        return arrList;
    }

    @Override
    public BookDTO getBookInfo(Long id) {
        Optional<Book> list = bookRepository.findById(id);
        Book entity = list.get();
        BookDTO result = new BookDTO(entity.getId(), entity.getTitle(), entity.getPublish(),
                entity.getPrice(), entity.getBookImage());
        return result;

//        Optional<Book> list = bookRepository.findById(id);
//        List<BookDTO> arrList = list.stream().map(entity        // stream을 통해 for문 처럼 동작
//                ->new BookDTO(entity.getId(), entity.getTitle(),
//                entity.getPublish(), entity.getPrice(), entity.getBookImage()))
//                .collect(Collectors.toList());
//
//        return arrList.get(0);
    }

    @Override
    public Book saveBook(BookDTO bookDTO) {
        // dto =========> entity로 컨버팅
        Book createEntity = Book.builder()
                .title(bookDTO.getTitle())
                .publish(bookDTO.getPublish())
                .price(bookDTO.getPrice())
                .bookImage(bookDTO.getBookImage())
                .build();
        Book entity = bookRepository.save(createEntity);
        return entity;
    }// saveBook-----------------------




    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(BookDTO bookDTO) {
        // Entity와 DTO 간에 property명(멤버변수)이 같으면 objectMapper를 이용해서 변환할수 있다/
        //Book tmpEntity = objectMapper.convertValue(bookDTO, Book.class);

        Book existEntity = bookRepository.findById(bookDTO.getId())
                .orElseThrow(()-> new IllegalArgumentException("no data found"));

        BeanUtils.copyProperties(bookDTO, existEntity, "id");
        //bookDTO가 가진 프로퍼티 값을 existEntity로 카피를 하되 "id"를 제외시킴.


        Book updateEntity = bookRepository.save(existEntity); //tmpEntity);

        return updateEntity;
    }

    @Override
    public Page<BookDTO> getBooksPaging(String search, Pageable pageable) {
        Page<Book> books = null;
        if(search.isBlank()){
            log.info("************************");
            books=bookRepository.findAll(pageable);     //모든 데이터 가져오기
        }else{
            log.info("########################");
            books=bookRepository.findByTitleLikeIgnoreCase("%"+search+"%", pageable);
        }
        log.info("book======={}", books);
        // Page<Book> =======> Page<BookDTO> 타입으로 변환해야 함
        Page<BookDTO> booksPage = books.map(entity->objectMapper.convertValue(entity, BookDTO.class));
        return booksPage;
    }

    @Override
    public List<BookReviewDTO> getReviewsForBook(Long bookId) {
        return reviewRepository.findByReviewsByBookId(bookId);
    }//------------------------------



}
