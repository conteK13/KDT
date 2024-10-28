package com.kosmo.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosmo.exception.NoMemberException;
import com.kosmo.model.MemberDAO;
import com.kosmo.model.MemberDTO;

/*
 * Get /login.do ==> 로그인 폼 보여주기
 * Post /login.do ==> 로그인 로직 처리(userId, userPw를 받아서 회원정보가 맞는지 체크) 
 */
@WebServlet("/login.do")
public class LoginFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// member/login.jsp  페이지로 forward 이동
		RequestDispatcher disp = req.getRequestDispatcher("member/login.jsp");
		disp.forward(req, res);
	}//---------------------------------

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1. 사용자가 입력한 아이디, 비밀번호 받기
		String userId = req.getParameter("userId");
		String userPw = req.getParameter("userPw");
		
		// 2. 유효성 체크
		if(userId==null || userPw==null || userId.trim().isBlank() || userPw.trim().isBlank()) {
			res.sendRedirect("login.do");
			return;
		}
		
		// 3. 1번에서 받은 값을 DTO에 담는다. tmpDTO
		MemberDTO tmpDTO = new MemberDTO(); //임시유저
		tmpDTO.setUserId(userId);
		tmpDTO.setUserPw(userPw);
		
		MemberDAO dao = new MemberDAO();
		try {
			// 4. MemberDAO의 loginChect(tempDTO)
			// ======> 아이디 비번이 일치하면 MemberDTO 객체 반환 =? loginUser
			// ======>일치 하지 않으면 사용자 정의 예외 발생.
			MemberDTO loginUser = dao.loginCheck(tmpDTO);
			
			// 5. loginUser가 null이 아니라면 index.html로 redirect 이동
			if(loginUser!=null){
				// 6. 세션에 loginUser정보 저장
				// http 프로토콜의 특징 : stateless(무상태) -> 상태정보를 유지하지 못함
				// 상태 정보를 유지하기 위해 Session(로그인에선 세션을 자주 사용) / Cookie 라는 기술을 사용한다.
				// Session : 서버쪽에 상태 정보를 저장
				// Cookie : 클라이언트쪽에 상태 정보를 저장
				HttpSession session = req.getSession();
				session.setAttribute("loginUser", loginUser); // request와 비슷한 동작
				
				res.sendRedirect("index.do");
			}
		} catch (NoMemberException e) {
			String msg = e.getMessage();
			String loc = "javascript:history.back()";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			RequestDispatcher disp = req.getRequestDispatcher("message.jsp");
			disp.forward(req, res);
		}

	}//---------------------------------

}