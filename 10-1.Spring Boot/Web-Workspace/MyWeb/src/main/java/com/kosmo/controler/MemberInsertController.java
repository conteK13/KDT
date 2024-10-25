package com.kosmo.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/signupEnd.do")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 0. post방식 한글처리
		req.setCharacterEncoding("utf-8");
		
		// 1. 사용자가 입력한 값 받기(name, userId, userPw, email)
		String name = (String) req.getParameter("name");
		String userId = (String)req.getParameter("userId");
		String userPw = (String)req.getParameter("userPw");
		String email = (String)req.getParameter("email");
		System.out.println(name+ " / "+userId);
		
		// 2. 유효성 체크(빈문자열 체크)
		if(name.trim().isBlank()|| userId.trim().isBlank()||userPw.trim().isBlank()) {
			res.sendRedirect("/signup.do");	//redirect방식으로 이동
											// jsp 접근x
			return;
		}
		
		// 3. Model 객체 생성(MemberDTO)해서 1번에서 받은 앖을 MemberDTO에 setting
		// 4. 			   (MemberDAO) 객체 생성 후 insert() 호출하고 결과값 받기
		
		
		// 5. req에 결과에 따른 값들을 저장
		int result = 1;		// insert문에 의해 영향받은 레코드 개수 반환할 예정
		String msg = (result > 0)? "회원가입 성공" : "회원가입 실패";
		String loc = (result > 0)? "/login.do" : "javascript:history.back()";
		
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);
		
		// 6. message.jsp로 forward이동
		RequestDispatcher disp = req.getRequestDispatcher("message.jsp");
		disp.forward(req, res);
	}
}
