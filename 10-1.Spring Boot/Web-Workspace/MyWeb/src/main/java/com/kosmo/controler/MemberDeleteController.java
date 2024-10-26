package com.kosmo.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosmo.model.MemberDAO;


@WebServlet("/memberDel.do")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// url에 포함된 parameter를 idx 변수에 받는다.
		String idx = req.getParameter("idx");
		
		// null과 빈문자열을 체크한다.
		if(idx == null || idx.trim().isBlank()) {
			System.out.println("idx누락");
			res.sendRedirect("/memberlist.do");	//redirect방식으로 이동
			return;
		}
		
		MemberDAO dao = new MemberDAO();
		int result = dao.delete(Integer.parseInt(idx.trim()));
		System.out.println("result : " + result);
		
		// 5. req에 결과에 따른 값들을 저장
		String msg = (result > 0)? "삭제 성공" : "삭제 실패";
		String loc = "/memberlist.do";
		
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);
		
		// 6. message.jsp로 forward이동
		RequestDispatcher disp = req.getRequestDispatcher("message.jsp");
		disp.forward(req, res);
	}
}
