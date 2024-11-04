package com.kosmo.board.service;

import com.kosmo.board.domain.BoardDTO;
import com.kosmo.board.domain.PagingDTO;

import java.util.List;

public interface BoardService {
    // 추상 메서드 선언

    // 글 쓰기
    int insertBoard(BoardDTO board);


    //글 목록, 검색 목록
    List<BoardDTO> listBoard(PagingDTO paging);
    //총 게시물 수 or 검색한 게시글 수
    int getTotalCount(PagingDTO paging);


    // 글번호(num  -pk)로 해당 글 가져오기
    BoardDTO findBoardByNum(int num);
    int updateReadnum(int num); // 조회수 증가


    // 글번호로 글 삭제
    int deleteBoardByNum(int num);


    // 글 수정
    int updateBoard(BoardDTO board);
}
