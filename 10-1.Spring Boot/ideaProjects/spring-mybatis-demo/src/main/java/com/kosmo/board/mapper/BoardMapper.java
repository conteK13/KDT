package com.kosmo.board.mapper;

import com.kosmo.board.domain.BoardDTO;
import com.kosmo.board.domain.PagingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insertBoard(BoardDTO board);
    List<BoardDTO> listBoard(PagingDTO paging);

    int getTotalCount(PagingDTO paging);
    BoardDTO findBoardByNum(int num);
    int updateReadnum(int num);
    int deleteBoard(int num);

    int updateBoard(BoardDTO board);
}
