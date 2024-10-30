package com.kosmo.user.mapper;

import com.kosmo.user.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
// xxxMapper 인터페이서를 선언하면
// 해당 인터페이스를 구현한객체를 스프링 컨테이너가 알아서 제공한다
// MapperProxy 객체
// mapper 파일이 위치한 곳 => application.properties 를 찾아서
// 해당 매퍼파일(xml)을 읽어서 실행시킴

@Mapper
public interface MemberMapper {

    int insertMember(MemberDTO user);
    List<MemberDTO> listMember();
    MemberDTO findMemberByIdx(int idx);
    MemberDTO findMemberByUserId(String userId);
    int deleteMemberByIdx(int idx);
    int updateMember(MemberDTO user);
}
