package com.kosmo.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosmo.model.MemberDAO;
import com.kosmo.model.MemberDTO;


@WebServlet("/memberlist.do")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1. MemberDAO객체의 list()를 호출
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> arrList=dao.list();
		
		// 2. 그 결과를 받아서 req에 저장
		req.setAttribute("members", arrList);
		
		// 3. 뷰페이지로 forward 이동
		String viewPage = "member/memberAll.jsp";
		RequestDispatcher  disp = req.getRequestDispatcher(viewPage);
		disp.forward(req, res);
	}

}
