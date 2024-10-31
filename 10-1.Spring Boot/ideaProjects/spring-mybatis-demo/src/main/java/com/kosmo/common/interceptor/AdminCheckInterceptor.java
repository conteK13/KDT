package com.kosmo.common.interceptor;

import com.kosmo.user.domain.MemberDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

//HandlerInterceptor 상속받아 구현하세요
// 세션에 저장된 loginUser를 꺼내서, getMstate()값이 9가 아니라면
// "관리자만 이용가능합니다". "/main"으로 이동시키기
// 구현 후 WebConfig에 addInterceptor() registry에 등록하기

@Component
public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
                             Object handler)
            throws ServletException, IOException {

        HttpSession ses = req.getSession();
        MemberDTO member = (MemberDTO) ses.getAttribute("loginUser");
        if(member.getMstate() != 9){
            req.setAttribute("msg", "관리자만 이용가능합니다.");
            req.setAttribute("loc", "/main");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/message.jsp");
            dispatcher.forward(req, res);
            return false;   //false를 반환하면 컨트롤러로 넘어가지 못함
        }// ------------------
        return true;
    }
}
