package com.kosmo.user.repository;

import com.kosmo.user.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

    // email로 사용자 정보 가져오기
    Optional<MyUser> findByEmail(String email);
}
