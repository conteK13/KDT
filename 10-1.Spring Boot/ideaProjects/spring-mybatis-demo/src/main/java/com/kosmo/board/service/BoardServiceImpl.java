package com.kosmo.board.service;

import com.kosmo.board.domain.BoardDTO;
import com.kosmo.board.domain.PagingDTO;
import com.kosmo.board.mapper.BoardMapper;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("boardServiceImpl")
@Slf4j
public class BoardServiceImpl implements BoardService{

    @Inject     //@Autowired와 동일. byType으로 주입.
    private BoardMapper boardMapper;


    @Override
    public int insertBoard(BoardDTO board) {
        log.info("boardMapper={}", boardMapper);
        return boardMapper.insertBoard(board);
    }

    @Override
    public List<BoardDTO> listBoard(PagingDTO paging) {
        return boardMapper.listBoard(paging);
    }

    @Override
    public int getTotalCount(PagingDTO paging) {
        return boardMapper.getTotalCount(paging);
    }

    @Override
    public BoardDTO findBoardByNum(int num) {
        return boardMapper.findBoardByNum(num);
    }

    @Override
    public int updateReadnum(int num) {
        return 0;
    }

    @Override
    public int deleteBoardByNum(int num) {
        return 0;
    }

    @Override
    public int updateBoard(BoardDTO board) {
        return 0;
    }
}
