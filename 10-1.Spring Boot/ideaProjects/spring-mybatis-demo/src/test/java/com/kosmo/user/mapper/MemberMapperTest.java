package com.kosmo.user.mapper;

import com.kosmo.spring_mybatis_demo.SpringMybatisDemoApplication;
import com.kosmo.user.domain.MemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = SpringMybatisDemoApplication.class)
@Transactional  //테스트 후 데이터 베이스에 롤백을 한다.
@Rollback(false)    //false를 줘야 DB에 들어갔는지 확인할 수 있다.
class MemberMapperTest {
    @Autowired  //byType으로 객체를 주입하는 어노테이션
    private MemberMapper userMapper;

    @Test
    public void testInsertMember(){
        // Given(선행 조건) - When(실행) - Then(검증)
        // Given : 선행 데이터/조건
        MemberDTO user= new MemberDTO();
        user.setName("최봄");
        user.setUserId("spring2");
        user.setUserPw("123");
        user.setEmail("sp@test.com");
        user.setMstate(1);

        // When : 실행
        int result = userMapper.insertMember(user);

        // Then : 검증
        Assertions.assertEquals(1,result);
    }
}