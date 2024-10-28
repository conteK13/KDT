package com.kosmo.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosmo.model.MemberDAO;
import com.kosmo.model.MemberDTO;
// GET /memberInfo.do?idx=10 ==> 회원정보 가져오기(select)
// POST / memberInfo.do		 ==> 회원정보 수정하기(update)

@WebServlet("/memberInfo.do")
public class MemberEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1. 회원번호 받기
		String idx = req.getParameter("idx");
		
		// 2. 유효성 체크
		if(idx ==null || idx.trim().isBlank()) {
			System.out.println("idx null or blank");
			res.sendRedirect("memberlist.do");
			return;
		}
		
		// 3. MemberDAO의 getMember(idx) 호출  ===> memberDTO 
		MemberDAO dao = new MemberDAO();
		MemberDTO user = dao.getMember(Integer.parseInt(idx.trim()));
		
		
		// 4. req에 MemberDTO객체에 저장
		req.setAttribute("user", user);
		
		
		// 5. member/memberInfo.jsp로 forward이동		
		RequestDispatcher disp = req.getRequestDispatcher("member/memberInfo.jsp");
		disp.forward(req, res);
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1. Post방식일때 한글 처리
		req.setCharacterEncoding("utf-8");
		
		// 2. 수정한 회원정보 받기
		String idx = req.getParameter("idx");
		String name = req.getParameter("name");
		String userId = req.getParameter("userId");
		String email = req.getParameter("email");
		String mstate = req.getParameter("mstate");
		
		// 3. 유효성 체크(빈문자열 체크)
		if(idx.trim().isBlank() || name.trim().isBlank()|| userId.trim().isBlank()||email.trim().isBlank()) {
			res.sendRedirect("memberlist.do");	//redirect방식으로 이동
			return;
		}
		
		// 4. 2번에서 받은 값을 MemberDTO 객체에 담아주기
		MemberDTO user = new MemberDTO(Integer.parseInt(idx.trim()), name.trim(), userId.trim(),
				null, email.trim(), Integer.parseInt(mstate.trim()), null);
		
		// 5. MeberDAO 객체 생성 후 update(MemberDTO 객체) 호출하기
		MemberDAO dao = new MemberDAO();
		int result = dao.update(user);

		
		// 6. 받은 결과값에 따라 msg, loc 처리
		String msg = (result >0)? "회원정보 수정 성공" : "회원정보 수정 실패";
		String loc = (result >0)? "memberlist.do" : "javascript:history.back()";
		
		// 7. req에 msg, loc 저장
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);
		
		// 8. message.jsp로 forward이동
		RequestDispatcher disp = req.getRequestDispatcher("message.jsp");
		disp.forward(req, res);
		
	}//------------------------

}// class//////////////////
