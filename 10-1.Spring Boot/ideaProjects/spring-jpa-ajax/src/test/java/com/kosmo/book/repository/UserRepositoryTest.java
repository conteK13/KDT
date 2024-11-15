package com.kosmo.book.repository;

import com.kosmo.book.dto.UserDTO;
import com.kosmo.book.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
// @SpringBootTest
@Transactional
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    public void createUser(){
        User u1 = User.builder().name("김서영").userid("kim").build();
        User u2 = User.builder().name("서유미").userid("seo").build();
        User u3 = User.builder().name("이찬영").userid("lee").build();
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
    }   // -------------------------------------------------

    @Test
    public void 네임드쿼리_테스트(){
        this.createUser();
        List<User> list = userRepository.findByUserid("seo");
        User user = list.get(0);
        assertThat(user.getName()).isEqualTo("서유미");
        System.out.printf("Id : %d, Name : %s, Userid : %s%n", user.getId(), user.getName(), user.getUserid());

    }//-----------------------------------------------------

    @Test
    public void 쿼리테스트(){
        this.createUser();
        // 이름 and 아이디
        List<User> list = userRepository.findUser("김서영", "kim");
        assertThat(list.size()).isEqualTo(1);

        list=userRepository.findUser("홍길동", "lee");
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void 모든회원_이름목록찾기_테스트(){
        this.createUser();
        List<String> nameList = userRepository.findUserList();
        assertThat(nameList.size()).isEqualTo(3);
        nameList.stream().forEach(System.out ::println);
        System.out.println("-".repeat(30));
    }

    @Test
    public void testUserDTO(){
        this.createUser();
        List<UserDTO> userDTOS = userRepository.findUserDTO();
        userDTOS.stream().forEach(System.out::println);
        System.out.println("-".repeat(30));
    }

    @Test
    public void 컬렉션_바인딩_테스트(){
        this.createUser();
        List<User> userList = userRepository.findByNames(List.of("이찬영", "김서영", "홍길동"));
        assertThat(userList.size()).isEqualTo(2);

        System.out.println("*".repeat(30));
        userList.stream().forEach(System.out::println);
    }


}////////////////////////////////////////////////////