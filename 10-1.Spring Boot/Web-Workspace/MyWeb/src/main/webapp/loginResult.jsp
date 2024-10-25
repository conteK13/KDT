<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1> LoginResult.jsp 페이지</h1>

<%
	// request : 내장객체(httpServletRequest 타입)
	// response : 내장객체(httpServletResponse 타입)
	// request에 저장된 값을 꺼내자 Object request.getAttribute("key");

	String msg = (String) request.getAttribute("message");
	String loc = (String) request.getAttribute("loc");
	
%>

<h1> msg: <%=msg %></h1>
<h1> loc: <%=loc %></h1>