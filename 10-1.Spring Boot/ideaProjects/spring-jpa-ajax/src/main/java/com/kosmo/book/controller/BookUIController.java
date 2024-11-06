package com.kosmo.book.controller;

import com.kosmo.book.dto.BookDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/book/ui")
public class BookUIController {

    @GetMapping("/test")        //book/ui/test
    public String testUI(){

        return "hello";      // 뷰네임
        //  "/WEB-INF/views/hello.jsp"
    }

    @GetMapping(value = "/json", produces ={"application/json"})    // book/ui/json
    @ResponseBody
    public BookDTO getJsonData(){
        BookDTO dto = new BookDTO(1L, "Ajax 기초",
                "대림 출판사", 15000, "c.jpg");

        return dto;
    }

    @GetMapping(value = "/jsonArr", produces ={"application/json"})
    @ResponseBody
    public List<BookDTO> getJsonArrayData(){
        BookDTO dto1 = new BookDTO(1L, "Ajax 기초",
                "대림 출판사", 15000, "c.jpg");
        BookDTO dto2 = new BookDTO(2L, "Java 기초",
                "생능 출판사", 20000, "d.jpg");
        BookDTO dto3 = new BookDTO(3L, "Python 기초",
                "길벗 출판사", 25000, "e.jpg");

        return List.of(dto1,dto2,dto3);
    }
}
