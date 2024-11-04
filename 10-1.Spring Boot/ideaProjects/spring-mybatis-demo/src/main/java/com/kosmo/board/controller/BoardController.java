package com.kosmo.board.controller;

import com.kosmo.board.domain.BoardDTO;
import com.kosmo.board.domain.PagingDTO;
import com.kosmo.board.service.BoardService;
import com.kosmo.common.util.CommonUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class BoardController {

    @Resource(name = "boardServiceImpl")
    //byName 빈의 이름이 boardServiceImpl인 개체 주입
    private BoardService boardService;

    // @Autowired
    @Resource(name = "commonUtil")
    private CommonUtil util;

    @GetMapping("/board/form")
    public String boardForm(){
        log.info("boardService = {}", boardService);
        return "board/boardWrite";
    }

    @PostMapping("/user/write")
    public String boardWrite(BoardDTO board, Model model,
                             @RequestParam("mfileName") MultipartFile mfileName,
                             HttpServletRequest req){
        log.info("board = {}", board);
        log.info("board = {}", mfileName);

        String msg = "test";
        String loc = "/board/list";
        // 1. 유효성 체크
        if(board.getUserId().trim().isBlank()|| board.getPasswd().trim().isBlank()){
            return "redirect:/board/form";
        }

        // 2. 파일 업로드 처리
        // 2-1) 업로드할 디렉토리 절대 경로 얻기
        String upDir = req.getServletContext().getRealPath("/board_upload");
        log.info("upDir : {}", upDir);
        File dir = new File(upDir);
        if(!dir.exists()){
            dir.mkdirs();
        }

        if(!mfileName.isEmpty()){   // 첨부파일이 있다면
            String fname = mfileName.getOriginalFilename();     // 원본 파일명
            long fsize = mfileName.getSize();       // 첨부파일 크기

            //물리적 파일명
            UUID uuid = UUID.randomUUID();
            String fileName = uuid.toString()+"_"+fname;
            log.info("fileName={}", fileName);
            board.setFileName(fileName);
            board.setOriginFilename(fname);
            board.setFileSize(fsize);

            //파일 업로드 확인
            try {
                mfileName.transferTo(new File(upDir, fileName));
                log.info("업로드 성공!!!");
            }catch (IOException ex){
                log.error("파일 업로드 실패" +ex);
            }

        }//if----------

        // 3. 글쓰기 or 수정
        int n = 0;
        if(board.getMode().equals("write")){
            //글쓰기 처리
            n = boardService.insertBoard(board);
            msg = (n>0)? "글쓰기 성공" : "글쓰기 실패";
        }else if(board.getMode().equals("edit")){
            // 수정 처리
            n = boardService.updateBoard(board);
            msg = (n>0)? "글 수정 성공" : "글 수정 실패";
        }
        loc = (n>0)? "/board/list" : "javascript:history.back()";
        return util.addMsgLoc(model, msg, loc);
    }// -----------------------------


    @GetMapping("/board/list")
    public String boardList(Model model, PagingDTO paging){
        // 1. 총 게시물 수 가져오기
        int totalCount = boardService.getTotalCount(paging);

        // 2. 게시물 가져오기
        List<BoardDTO> list = boardService.listBoard(paging);

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("boardList", list);

        return "board/boardList";
    }

    @GetMapping("/board/view")
    public String boardView(@RequestParam("num") int num, Model model) {
        BoardDTO board = boardService.findBoardByNum(num);

        model.addAttribute("board", board);

        return "board/boardView";
    }
}
