package com.kosmo.book.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.basic.entity.BookEntity;
import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.entity.Book;
import com.kosmo.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;
    //Entity와 DTO 객체를 변환할때 사용

    @Override
    public List<BookDTO> getAllBooks(String search) {
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
}
