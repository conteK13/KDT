package com.kosmo.user.controller;

import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserViewController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(){
        return "login";// 뷰네임
        //타임리프
        // resources/templates/login.html
    }//-------------------

    @GetMapping("/signup")
    public String joinForm(){
        return "signup";
        // resources/templates/signup.html
    }//------------

    @PostMapping("/signup")
    public String joinEnd(UserDTO user){
        log.info("user==========={}",user);
        // 회원가입 처리
        Long id = userService.save(user);
        log.info("가입한 회원 id==={}",id);
        return "redirect:/login";
    }


}/////////////////////
