package com.kosmo.book.controller;

import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.dto.ResponseDTO;
import com.kosmo.book.entity.Book;
import com.kosmo.book.service.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController             // @Controller @ResponseBody를 합친 컨트롤러(Ajax 전용 컨트롤러)
@RequestMapping("/api/books")
@RequiredArgsConstructor    // 생성자 인젝션(final 필드 주입)
public class BookRestController {
    private final BookService bookService;


    @PostMapping("")
    public ResponseEntity<ResponseDTO> bookSave(@RequestBody BookDTO dto){
        // JSON 데이터를 받으려면 dto 앞에 @RequestBody를 붙여주자.
        log.info("dto========{}",dto);
        Book entity = bookService.saveBook(dto);
        log.info("entity======{}", entity);

        String status =(entity==null)?"fail":"success";
        String message = (entity ==null)?"도서등록 실패" : "도서등록 완료";

        ResponseDTO resDto = new ResponseDTO(status, message);
        return  ResponseEntity.status(200).body(resDto);
    }

    // GET /api/books ==> 모든 도서 정보 가져오기
    @GetMapping("")
    public List<BookDTO> geyAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookInfo(@PathVariable("id") Long id){
        //log.info("id=========={}",id);
        BookDTO result = bookService.getBookInfo(id);
        log.info("result = {}",result);
        return result;
    }

    @GetMapping("/json")        // api/books/json
    public BookDTO testDTO(){
        return new BookDTO(100L, "Restful API", "한빛 출판사", 5000, "restful_api.jpg");
    }
    
    @GetMapping("/jsonArr")
    public List<BookDTO> testArrayList(){
        BookDTO b1 = new BookDTO(100L, "Restful API", "한빛 출판사", 5000, "1.jpg");
        BookDTO b2 = new BookDTO(200L, "SpringBoot3", "메이콘 출판사", 25000, "2.jpg");
        BookDTO b3 = new BookDTO(300L, "JPA", "길벗", 5000, "3.jpg");
        return List.of(b1, b2, b3);
    }
}
