package com.kosmo.user.service;

import com.kosmo.user.domain.MemberDTO;
import com.kosmo.user.exception.NoMemberException;
import com.kosmo.user.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// 컨트롤러 계층에는 @Controller, @RestController를 붙인다.
// 서비스 계층에는 @Service을 붙인다.
// 영속성 계층(Persistence , DAO 같은 거) 계층 : @Mapper(MyBatis) / @Repository(JPA)

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberMapper userMapper;

    // 생성자 인젝션 ==> MemberMapper를 구현한 객체를 생성자의 인자로 넘긴다.
    @Autowired  //==>생략해도 자동으로 된다.
    public MemberServiceImpl(MemberMapper userMapper) {
        this.userMapper = userMapper;
        log.info("userMapper={}", userMapper);      // Proxy 객체
    }

    @Override
    public int insertMember(MemberDTO user) {
        return userMapper.insertMember(user);
        // 스프링은 namespace가 com.kosmo.user.mapper.MemberMapper로 되어 있는 xml파일을 찾는다.
        // 찾았다면 id가 insertMember로 되어있는 SQL문을 찾아 실행시킨다.
    }

    @Override
    public List<MemberDTO> listMember() {
        return userMapper.listMember();
    }

    @Override
    public MemberDTO findMemberByUserId(String userId) {
        return userMapper.findMemberByUserId(userId);
    }

    @Override
    public MemberDTO findMemberByIdx(int idx) {
        return userMapper.findMemberByIdx(idx);
    }

    @Override
    public int deleteMemberByIdx(int idx) {
        return userMapper.deleteMemberByIdx(idx);
    }

    @Override
    public int updateMember(MemberDTO user) {
        return userMapper.updateMember(user);
    }

    @Override
    public boolean idCheck(String userId) {
        MemberDTO user = userMapper.findMemberByUserId(userId);
        
        if (user!=null) {    // 이미 사용중
            return false;
        }
        // 사용가능
        return true;
    }

    @Override
    public MemberDTO loginCheck(MemberDTO tmpUser) throws NoMemberException {
        MemberDTO dbUser = userMapper.findMemberByUserId(tmpUser.getUserId());
        if(dbUser==null){
            // 아이디가 없는 경우
            throw new NoMemberException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        if(!dbUser.getUserPw().equals(tmpUser.getUserPw())) {
            // 비밀번호가 일치하지 않는 경우
            throw new NoMemberException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        // 탈퇴회원인 경우
        if(dbUser.getMstate()<0) {
            throw new NoMemberException("회원이 아닙니다. 회원 가입 하세요");
        }

        return dbUser;
    }
}
