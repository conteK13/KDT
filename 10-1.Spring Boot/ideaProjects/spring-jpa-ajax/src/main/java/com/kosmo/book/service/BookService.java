package com.kosmo.book.service;


import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.dto.BookReviewDTO;
import com.kosmo.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks(String search);
    BookDTO getBookInfo(Long id);
    Book saveBook(BookDTO bookDTO);
    void deleteBook(Long id);

    Book updateBook(BookDTO bookDTO);

    Page<BookDTO> getBooksPaging(String search, Pageable pageable);

    List<BookReviewDTO> getReviewsForBook(Long bookId);
}
