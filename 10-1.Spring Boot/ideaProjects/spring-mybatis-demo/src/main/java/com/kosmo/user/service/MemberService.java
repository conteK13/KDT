package com.kosmo.user.service;


import com.kosmo.user.exception.NoMemberException;
import com.kosmo.user.domain.MemberDTO;

import java.util.List;

public interface MemberService {
    int insertMember(MemberDTO user);
    List<MemberDTO> listMember();
    MemberDTO findMemberByUserId(String userId);
    MemberDTO findMemberByIdx(int idx);
    int deleteMemberByIdx(int idx);
    int updateMember(MemberDTO user);

    boolean idCheck(String userId);
    MemberDTO loginCheck(MemberDTO tmpUser) throws NoMemberException;

}
