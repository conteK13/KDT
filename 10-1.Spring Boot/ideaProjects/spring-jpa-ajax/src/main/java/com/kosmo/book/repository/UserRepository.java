package com.kosmo.book.repository;

import com.kosmo.book.dto.UserDTO;
import com.kosmo.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//[1] Query Method[o]
//[2] JPQL  [o]
//[3] Named Query   [x]
public interface UserRepository extends JpaRepository<User, Integer> {
    // Named Query 적용. 파라미터에는 @Param 잘 붙여주자.
    @Query(name= "User.findByUserid")
    List<User> findByUserid(@Param("userid") String userid);

    @Query("select u from User u where u.name=:name and u.userid=:userid")
    List<User> findUser(@Param("name") String name, @Param("userid") String userid);
    // 쿼리 메서드 findByNameAndUserid(String name, String userid)

    // 모든 회원들의 이름 회원번호 가져오기
    @Query("select u.name from User u")
    List<String> findUserList();

    // UserDTO 객체로 받기
    @Query("select new com.kosmo.book.dto.UserDTO(u.id, u.name, u.userid) from User u")
    List<UserDTO> findUserDTO();

    //컬렉션 바인딩하는 경우
    @Query("select u from User u where u.name in :names")
    List<User> findByNames(@Param("names") List<String> names);
}
