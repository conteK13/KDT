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
	input[type="radio"]{
		width : 20px;
	}
	
	span.mstate1{
	color:blue;}
	
	span.mstate0{
	color:gray;}
	
	span.mstate-1{
	color:red;}
	
	span.mstate9{
	color:green;}
	
	span{
		font-weight:bold;
	}
</style>

<c:if test="${user==null}">
	<script>
		alert('해당 회원은 존재하지 않아요')
		history.back();
	</script>
</c:if>
<c:if test="${user!=null}">
	<div class = "container">
	<!--
	    메소드에 따라 로직을 다르게 구성(RESTful 서비스)
	    GET: 조회(Read)
	    POST: 삽입(Insert, Create)
	    PUT: 수정(Update)
	    DELETE : 삭제(Delete)
	-->
		<form action = "memberUpdate" method="post">
			<table>
				<tr> 
					<th colspan = "2"> <h1>MemberInfo-회원정보수정</h1> </th>
				</tr>
				<tr>
					<th> 회원번호 </th>
					<td> 
						<input type = "text" id = "idx" name = "idx"
						value="${user.idx}"
						placeholder="idx" readonly>
					</td>
				</tr>
				<tr>
					<th> 이름 </th>
					<td> 
						<input type = "text" id = "name" name = "name"
						value="${user.name}"
						placeholder="Name">
					</td>
				</tr>			
				<tr>
					<th> 아이디 </th>
					<td> 
						<input type = "text" id = "userId" name = "userId"
						value="${user.userId}"
						placeholder="ID">
					</td>
				</tr>
				<tr>
					<th> 이메일 </th>
					<td> 
						<input type = "email" id = "email" name = "email"
						value="${user.email}"
						placeholder="email">
					</td>
				</tr>
				<tr>
					<th> 회원상태정보 </th>
					<td> 
						<span class = "mstate${user.mstate}"> ${user.mstateStr} </span>
						<br><br>
						<label for="mstate1">
							<input type = "radio" name = "mstate" value = "1"
							<c:if test="${user.mstate==1}">
								checked
							</c:if>
							id = "mstate1">
							활동회원
						</label>
						<label for="mstate2">
							<input type = "radio" name = "mstate" value = "0"
							<c:if test="${user.mstate==0}">
								checked
							</c:if>
							id = "mstate2">
							정지회원
						</label>
						<label for="mstate3">
							<input type = "radio" name = "mstate" value = "-1"
							<c:if test="${user.mstate==-1}">
								checked
							</c:if>
							id = "mstate3">
							탈퇴회원
						</label>
						<label for="mstate4">
							<input type = "radio" name = "mstate" value = "9"
							<c:if test="${user.mstate==9}">
								checked
							</c:if>
							id = "mstate4">
							관리자
						</label>
					</td>
				</tr>
				<tr>
					<td colspan ="2" style="text-align:center">
						<br>
						<button type = "submit"> 수정하기 </button>
						<button type = "reset"> 다시쓰기 </button>
						<button type = "button" onclick="location.href='memberlist.do'"> 회원 목록 </button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</c:if>