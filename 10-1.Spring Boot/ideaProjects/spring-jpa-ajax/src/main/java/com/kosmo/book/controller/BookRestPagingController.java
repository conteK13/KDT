package com.kosmo.book.controller;

import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.dto.ResponseDTO;
import com.kosmo.book.entity.Book;
import com.kosmo.book.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController             // @Controller @ResponseBody를 합친 컨트롤러(Ajax 전용 컨트롤러)
@RequestMapping("/books")   // "books"로 수정하기(충돌나지 않도록)
@RequiredArgsConstructor    // 생성자 인젝션(final 필드 주입)
public class BookRestPagingController {
    private final BookService bookService;

    @PostMapping("")    // 파일 업로드 기능이 들어갈 경우
    public ResponseEntity<ResponseDTO> bookServeAndUp(BookDTO dto, @RequestParam(name="mbookImage", required = false)MultipartFile mbookImage,
                                                      HttpServletRequest req){

        log.info("DTO ===================={}",dto);
        log.info("mbookImage -------------{}", mbookImage);
        String fname = fileUpload(req, mbookImage);

        dto.setBookImage(fname);
        Book entity = bookService.saveBook(dto);
        log.info("entity======{}", entity);

        String status =(entity==null)?"fail":"success";
        String message = (entity ==null)?"도서등록 실패" : "도서등록 완료";

        ResponseDTO resDto = new ResponseDTO(status, message);
        return ResponseEntity.status(200).body(resDto);
    }// -----------------------------


    public String fileUpload(HttpServletRequest req, MultipartFile mbookImage){
        String upDir = req.getServletContext().getRealPath("/images");
        log.info("upDir======={}", upDir);

        File dir = new File(upDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String fname = null;
        try{
            if (mbookImage!=null) {
                fname = UUID.randomUUID().toString() + "_" + mbookImage.getOriginalFilename();
                if (!mbookImage.isEmpty()) {
                    mbookImage.transferTo(new File(upDir, fname));
                    log.info("파일 업로드 성공" + fname);
                }
            }
        }catch(IOException e){
            throw new RuntimeException("File Upload Error");
        }
        return fname;
    }


    @PostMapping("/old")    // 파일 업로드를 안할 경우
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

    // GET /books ==> 모든 도서 정보 가져오기
    @GetMapping("") //변환xkdlq 수정 : Map<String, Object>
    public Map<String, Object> getAllBooks(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy).ascending());
        Map<String, Object> map = new HashMap<>();
        log.info("search======={}",search);
        Page<BookDTO> bookPage = bookService.getBooksPaging(search, pageable);
        // 페이징 블럭 관련 로직
        int pagingBlock = 5;    // 페이징 블럭 단위 : 5
        // Preb | 1 ~ 5 | next
        int currentPage=bookPage.getNumber();   //현재 보여줄 페이지
        int totalPages = bookPage.getTotalPages();

        // JPA에서는 페이징 블럭처리는 해주지 않는다. 우리가 연산해야 함.
        int blockStart = (currentPage/pagingBlock)*pagingBlock +1;
        int blockEnd = Math.min(blockStart+pagingBlock-1, totalPages);
        //////////////////////////////////////////////////////
        map.put("books", bookPage.getContent());    // 도서 목록
        map.put("search", search);
        map.put("totalPages", bookPage.getTotalPages());    // 총 페이지
        map.put("totalCount", bookPage.getTotalElements()); // 총 도서 개수
        map.put("currentPage", bookPage.getNumber());       // 현재 페이지 번호
        map.put("blockStart", blockStart);          // 페이징 시작 값
        map.put("blockEnd", blockEnd);              // 페이징 끝 값
        map.put("pagingBlock", pagingBlock);        // 페이징 블럭 단위 : 5
        map.put("isFirst",bookPage.isFirst());      // 첫페이지 여부 : boolean 타입으로 반환
        map.put("isLast", bookPage.isLast());       // 마지막 페이지 여부 : boolean 타입으로 반환

        return map;
    }

    @GetMapping("/{id}")
    public BookDTO getBookInfo(@PathVariable("id") Long id){
        //log.info("id=========={}",id);
        BookDTO result = bookService.getBookInfo(id);
        log.info("result = {}",result);
        return result;
    }

    @PutMapping("")
    public ResponseEntity<ResponseDTO> updateBookFileUp(BookDTO bookDTO,
                            @RequestParam(name="mbookImage") MultipartFile mbookImage,
                            HttpServletRequest req){
        log.info("bookDTO==========={}", bookDTO);
        String fname= fileUpload(req, mbookImage);  //파일 업로드 처리
        bookDTO.setBookImage(fname);

        Book updateBook = bookService.updateBook(bookDTO);
        String status = (updateBook==null)? "fall":"success";
        String message = (updateBook ==null)? "도서정보 수정 실패" :"수정완료";

        ResponseDTO responseDTO = new ResponseDTO(status, message);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/old") //파일 업로드 안할 경우
    public ResponseEntity<ResponseDTO> updateBook(@RequestBody BookDTO bookDTO){
        log.info("bookDTO==========={}",bookDTO);
        Book updateBook = bookService.updateBook(bookDTO);
        String status = (updateBook==null)? "fall":"success";
        String message = (updateBook ==null)? "도서정보 수정 실패" :"수정완료";

        ResponseDTO responseDTO = new ResponseDTO(status, message);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable("id") Long id) {
        log.info("id======{}", id);
        bookService.deleteBook(id);

        return ResponseEntity.ok(new ResponseDTO("success", "삭제 완료"));
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
