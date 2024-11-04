package com.kosmo.board.domain;

import lombok.Data;

@Data
// 페이징 처리 및 검색 기능 모듈화 하여 재사용 가능하도록 처리하는 객체
public class PagingDTO {
    // 페이징 처리 관련 프로퍼티
    private int pageNum;            // 현재 보여줄 페이지 번호(1, 2, 3, ...);
    private int oneRecordPage = 5;  // 한 페이지 당 보여줄 목록 개수
    private int totalCount;         // 총 게시글 수. DB에서 가져와 설정할 예정
    private int pageCount;          // 총 게시글 수와 oneRecordPage와의 연산으로 설정;

    // DB에서 레코드를 끊어오기 위한 프로퍼티
    private int start;
    private int end;


    // 페이징 블럭처리를 위한 프로퍼티
    private int prevBlock;          // 이전 5개
    private int nextBlock;          // 이후 5개
    private int pagingBlock = 5;

    // 검색 관련 프로퍼티
    private String findType;        // 검색유형
    private String findKeyword;     // 검색어

    public void init(){
        // 페이지 수 구하기
        pageCount=(totalCount-1)/oneRecordPage +1;
        if(pageNum<1){
            pageNum=1;              // 1페이지를 기본값으로 설정
        }

        if(pageNum>pageCount){
            pageNum=pageCount;      // 마지막 페이지로 설정
        }

        // DB에서 끊어서 가져올 변수값 연산하기(start, end)
        end = pageNum*oneRecordPage;
        start = end-(oneRecordPage-1);
        // where rn between 1 and 5

    }

}// class ////////////////////////
