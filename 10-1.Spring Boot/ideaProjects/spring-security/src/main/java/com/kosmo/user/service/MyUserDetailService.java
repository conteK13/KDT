package com.kosmo.user.service;

import com.kosmo.user.entity.MyUser;
import com.kosmo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// UserDetailsService 구현    : 시큐리티에서 DB에서 사용자 정보를 가져오는 로직 구성
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // DB에서 email을 가지고 회원이 맞는지 체크를 한 뒤 맞다면 UserDetails
        // 객체를 반환한다. => MyUser 엔티티면서 UserDetails를 상속
        Optional<MyUser> opt = userRepository.findByEmail(email);
        opt.orElseThrow(()-> new UsernameNotFoundException("인증 실패"));
        return opt.get();
    }
}
