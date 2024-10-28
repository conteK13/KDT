package com.kosmo.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kosmo.model.MemberDTO;

/**
 * Servlet Filter implementation class AdminCheckFilter
 */
@WebFilter("/memberlist.do")
public class AdminCheckFilter extends HttpFilter implements Filter {
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		// loginUser가 있는지 확인
		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
		if(loginUser == null||loginUser.getMstate() != 9) {
			req.setAttribute("msg", "관리자만 이용할 수 있습니다.");
			req.setAttribute("loc", "index.do");
			RequestDispatcher disp = req.getRequestDispatcher("message.jsp");
			disp.forward(req, response);
						
			//로그인을 하지 않았다면 다음 필터로 가지 못하게 return;
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
