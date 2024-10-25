<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1> JoinResult.jsp 페이지</h1>
<%
	String msg = (String) request.getAttribute("message");
	String loc = (String) request.getAttribute("loc");
%>

<script>
	alert('<%=msg%>');
	location.href='<%=loc%>';
</script>