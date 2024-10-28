<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- -------------------- -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- -------------------- -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>index</title>
	<link rel = "stylesheet" type="text/css" href = "CSS/index.css">
	<style>
		ul{	width : 70%;
			margin : auto;
			/* border : 1px solid red; */
		}
		ul li {
			list-style:none;
		}
		ul li a{
			text-decoration :none;
			font-size:1.2em;
		}
	</style>
</head>
<body>
	<h1> Index Page 입니다. </h1>
	<div>
		HTML은 정적인 파일입니다.
		<hr>
		<ul>
			<li><a href = "hello.jsp"> hello.jsp </a></li>
			<li><a href = "hello.do?name=홍길동"> GET-방식 요청 -helloServlet </a></li>
			<li><a href = "form.jsp"> POST방식 요청 - form을 통해 요청 보내기 </a></li>
			<!-- 세션에 저장된 loginUser가 있을 경우 => 로그인한 경우(a님 로그인 중, 로그아웃) -->
			<!-- 없을 경우 => 로그인 안한 경우 (회원가입, 로그인) -->
			<!-- SessionScope.loginUser지만, SessoinScope는 생략가능 -->
			<c:if test = "${loginUser == null}">
				<li><a href = "signup.do"> 회원가입 </a></li>
			</c:if>
			
			<li><a href = "memberlist.do"> 전체 회원목록 </a></li>
			<c:if test = "${sessionScope.loginUser == null}">
				<li><a href = "login.do"> 로그인 </a></li>
			</c:if>
			<c:if test= "${loginUser != null }">
				<li><a href = "#"> <span style="color:red; font-weight:bold; font-size:1.2em;">${loginUser.userId}</span>님 로그인 중 </a></li>
				<li><a href = "logout.do"> 로그아웃 </a></li>
			</c:if>
			<li><a href = "mypage.do"> MyPage </a></li>
		</ul>
	</div>
</body>
</html>