package com.kosmo.book.service;

import com.kosmo.basic.entity.BookEntity;
import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.entity.Book;
import com.kosmo.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> list= bookRepository.findAll();
        // repository에서 받은 데이터는 엔티티 객체
        // ==>표현 계층에 전달 할때는 DTO로 변환해야 한다.
        List<BookDTO> arrList = list.stream().map(entity        // stream을 통해 for문 처럼 동작
                ->new BookDTO(entity.getId(), entity.getTitle(),
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
    }

    @Override
    public void deleteBook(Long id) {

    }
}
