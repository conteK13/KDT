package com.kosmo.user.controller;


import com.kosmo.user.domain.MemberDTO;
import com.kosmo.user.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        String msg = (n>0)? "회원가입 성공-로그인 페이지로 이동합니다" : "회원가입 실패";
        String loc = (n>0)? "/login" : "javascript:history.back()";
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        return "message";
        //"WEB-INF/views/message.jsp
    }// joinProcess------------------

    @RequestMapping("/admin/users")
    public String memberList(Model model){
        List<MemberDTO> arrList = userService.listMember();
        model.addAttribute("members", arrList);

        return "member/memberAll";
    }// memberList-------------------

    @RequestMapping(value = "/admin/memberInfo", method = RequestMethod.POST)
    public String memberInfo(Model model, @RequestParam(defaultValue = "0") int idx){
        // -parameters를 설정하면 @RequestParam을 생랼할 수 있다.
        log.info("idx: {}", idx);

        if(idx ==0){
            return "redirect:users";
        }
        // idx (PK) ==> 단일 객체를 반환
        MemberDTO user = userService.findMemberByIdx(idx);
        model.addAttribute("user", user);
        return "member/memberInfo";
    }//-memberInfo-----------------------

    @PostMapping("/admin/memberUpdate")
    public String memberUpdate(MemberDTO user, Model model){
        log.info("user = {}", user);
        if(user.getIdx()==0| user.getName().trim().isBlank() ||
                user.getUserId().trim().isBlank()){
            return "redirect:users";
        }
        int n = userService.updateMember(user);

        String msg = (n>0)? "수정 성공": "수정 실패";
        String loc = (n>0)? "users":"javascript:history.back()";
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        return "message";
    }// memberUpdate-------------------

    @PostMapping("/admin/memberDelete")
    public String memberDelete(@RequestParam(defaultValue = "0") int idx, Model model){
        log.info("idx = {}", idx);
        if(idx==0){
            return "redirect:users";
        }
        int n = userService.deleteMemberByIdx(idx);

        String msg = (n>0)? "삭제 성공": "삭제 실패";
        String loc = "/admin/users";
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        return "message";
    }// memberDelete-------------------

    @GetMapping("/idCheck")
    public String idCheck(){
        return "member/idCheck";
    }

    @PostMapping("idCheck")
    public String idCheckResult(Model model, @RequestParam(defaultValue = "") String userId){
        if(userId.trim().isBlank()){
            model.addAttribute("msg", "아이디를 입력하세요");
            model.addAttribute("loc", "javascript:history.back()");
            return "message";
        }
        boolean isUse = userService.idCheck(userId.trim());
        String msg = (isUse)? userId+"는 사용 가능합니다." : userId+"는 이미 사용중입니다.";
        String result = (isUse)? "ok":"fail";
        model.addAttribute("msg", msg);
        model.addAttribute("result", result);
        model.addAttribute("userId", userId);
        return "member/idCheckResult";
    }

}// class 끝 ===============================
