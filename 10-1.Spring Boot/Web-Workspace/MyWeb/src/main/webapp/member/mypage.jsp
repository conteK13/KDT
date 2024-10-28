<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		#wrap {
			width : 70%;
			margin : auto;
			padding : 1em;
		}
		
		#wrap h1{
			text-align:center;
		}
		fieldset{
			padding:1em;
			width:60%;
			margin : auto;
			background : beige;
		}
	</style>
</head>
<body>
	<div id = "wrap">
		<h1> MyPage </h1>
		<fieldset>
			<legend> :: ${loginUser.userId}님 정보::</legend>
			<h3>이름 : ${loginUser.name}</h3>
			<h3>아이디 : ${loginUser.userId}</h3>
			<h3>이메일 : ${loginUser.email}</h3>
			<h3>회원상태 : ${loginUser.mstateStr}</h3>		
		</fieldset>
		<br><br>
		<div style="text-align:center">
			<a href="index.do">Home</a>
		</div>
	</div>
</body>
</html>