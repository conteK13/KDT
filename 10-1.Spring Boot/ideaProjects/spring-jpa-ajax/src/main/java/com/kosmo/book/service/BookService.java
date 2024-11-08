package com.kosmo.book.service;


import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.entity.Book;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks(String search);
    BookDTO getBookInfo(Long id);
    Book saveBook(BookDTO bookDTO);
    void deleteBook(Long id);

    Book updateBook(BookDTO bookDTO);
}
