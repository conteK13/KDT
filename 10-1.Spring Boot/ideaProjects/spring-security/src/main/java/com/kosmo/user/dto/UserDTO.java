package com.kosmo.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;            // 권한(USER, ADMIN)

    private LocalDateTime createAt; // 가입일
    private LocalDateTime updatedAt;

}
