package com.kosmo.book.controller;

import com.kosmo.book.dto.BookDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController             // @Controller @ResponseBody를 합친 컨트롤러(Ajax 전용 컨트롤러)
@RequestMapping("/api/books")
public class BookRestController {
    
    @GetMapping("")
    public BookDTO testDTO(){
        return new BookDTO(100L, "Restful_api", "aaa", 5000, "1.jpg");
    }
}
