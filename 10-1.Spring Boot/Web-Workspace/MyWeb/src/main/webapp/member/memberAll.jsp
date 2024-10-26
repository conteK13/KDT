<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- -------------------------------- -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!-- -------------------------------- -->

<style>
	.container {
		width : 70%;
		margin : auto;
	}
	
	.container table{
		width : 100%;
		margin : auto
	}
	
	th, td {
		padding :5px;
	}
	
	input {
		padding : 4px;
		width : 60%;
		border : 1px solid #ddd;
	}
	
	#members th{
		background-color : #ddd;
	}
	
	#members td {
		border-bottom : 1px solid #ddd;
	}
	
	h1{
		text-align : center;
	}
</style>

<div class = "container">
	<h1> 회원 목록 [관리자 페이지 - Admin] </h1>
	
	<%-- ${members} --%>	<!-- ArrayList객체 -->
	 
	<table id = "members">
		<thead>
			<tr>
				<th> 번호 </th>
				<th> 이름 </th>
				<th> 아이디 </th>
				<th> 이메일 </th>
				<th> 가입일 </th>
				<th> 회원상태 </th>
				<th> 수정|삭제 </th>
			</tr>
		</thead>
		<tbody>
			<!-- ------ -->
			<c:if test="${members==null || empty members}">
				<tr>
					<td colspan = "7"> 데이터가 없습니다 </td>
				</tr>
			</c:if>
			
			<c:if test="${members !=null && not empty members}">
				<c:forEach var="user" items="${members}">
				<!--  user => MemberDTO 객체 참조, members ==> ArrayList<MemberDTO>객체 참조 -->
					<tr>
						<td> ${user.getIdx()} </td>
						<td> ${user.getName()} </td>
						<td> ${user.getUserId()} </td>
						<%-- ${user.userId} --%>
						<td> ${user.email} </td>
						<td> ${user.indate} </td>
						<td> ${user.getMstateStr()} </td>
						<td>
							<a href="memberInfo.do?idx=${user.idx}">수정</a> 
							<a href="javascript:remove('${user.idx}')">삭제</a> 
						</td>				
					</tr>
				</c:forEach>
			</c:if>
			<!-- ------ -->
		</tbody>
		
	</table>
	<script>
		function remove(idx){
			// alert(idx);
			let yn = window.confirm(idx+"번 회원정보를 삭제할까요?");
			if (yn) {
				location.href="/memberDel.do?idx="+idx;
				
			}
		}
	</script>
	
</div>