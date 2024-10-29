package com.kosmo.user.controller;


import com.kosmo.user.domain.MemberDTO;
import com.kosmo.user.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// @Autowired, @Inject, @Resource ==> 객체를 주입(DI) 할때 사용하는 이노테이션
// @Inject, Resource ==> 추가 라이브러리가 필요함. build.gradle에 등록해야 함
// DI 종류
// [1] Feild Injection
// [2] 생성자 Injection    ==> 3.2 버전부터 디폴트
// [3] setter Injection


@Controller
@Slf4j      // log를 사용할 수 있음.
public class MemberController {

    @Autowired
    private MemberService userService;      // 필드인젝션

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String joinForm(){
        return "member/signup";
        // "WEB-INF/views/member/signup.jsp"
    }// joinForm---------------------

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String joinProcess(MemberDTO user, Model model){
        // html의 input name과 MemberDTODML 객체의 변수명(Property)을 같게 준다면
        // 스프링이 알아서 MemberDTO 객체에 사용자가 입력한 값을 넣어준다.
        log.info("user: {}", user);
        if(user.getName()==null || user.getUserId()==null || user.getUserPw()==null || user.getName().trim().isBlank()
                || user.getUserId().trim().isBlank() || user.getUserPw().trim().isBlank()) {
            return "redirect:signup";
            // redirect:을 접두어로 붙여주면 redirect 방식으로 이동
            // 디폴트 이동방식은 forward이다.
        }
        // 서비스 객체의 메서드 호출
        int n = userService.insertMember(user);
        String msg = (n>0)? "회원가입 성공" : "회원가입 실패";
        String loc = (n>0)? "memberList" : "javascript:history.back()";
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        return "message";
        //"WEB-INF/views/message.jsp
    }// joinProcess------------------
}// class 끝 ===============================
