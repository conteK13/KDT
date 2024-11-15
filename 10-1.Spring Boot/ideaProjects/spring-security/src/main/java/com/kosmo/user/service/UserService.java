package com.kosmo.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.entity.MyUser;
import com.kosmo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ObjectMapper mapper;

    public Long save(UserDTO dto){
        String encodePasswd = passwordEncoder.encode(dto.getPassword());
        // 암호화 처리된 패스워드
        Long id = userRepository.save(MyUser.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(encodePasswd)    // 패스워드 암호화
                .role(dto.getRole()).build()).getId();
        return id;
    }


    public List<UserDTO> getUserList() {
        List<MyUser> list = userRepository.findAll(Sort.by("id").descending());
        List<UserDTO> userList = list.stream()
                .map(user->mapper.convertValue(user, UserDTO.class))
                .collect(Collectors.toList());

        return userList;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO getUser(Long id) {
        MyUser entity = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("없는 회원입니다."));
        UserDTO dto = mapper.convertValue(entity, UserDTO.class);
        return dto;
    }

    public void updateUser(UserDTO dto) {
        MyUser tmpUser1=userRepository.findById(dto.getId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다"));

        MyUser tmpUser2 = MyUser.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(tmpUser1.getPassword())
                .role(dto.getRole())
                .build();
        userRepository.save(tmpUser2);
    }
}
