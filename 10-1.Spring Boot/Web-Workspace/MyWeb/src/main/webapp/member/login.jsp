<%@page contentType="text/html; charset=utf-8"
pageEncoding = "utf-8" %>
<style>
	#wrap {
		width : 500px;
		height :350px;
		/* background : beige; */
		margin : 1em auto;
		padding : 1em;
}
	
</style>
 <script>
 // client 쪽에서 유효성 체크 ==> javascript
 // form이름.input이름.value ==> 사용자가 입력한값
 // document.getElementById('아이디명').value => 사용자가 입력한 값
 // document.querySelector("#아이디명").value => 사용자가 입력한 값
 	function check(){
		if(!frm.userId.value){
			alert('아이디를 입력하세요');
			frm.userId.focus();	// userId에 포커스 이동
			return false;
		}
		if(!frm.userPw.value){
			alert('비밀번호를 입력하세요');
			frm.userPw.focus();
			return false;
		}
		return true;
	}
</script>
 
 <div id="wrap">
       <h1>Login Page(login.do)</h1>
       <form  name = "frm" action="login.do" method="post" onsubmit="return check()">
       	<!-- onsubmit="return check()"를 하면 false가 반환되면 submit을 하지 않는다. -->
          아이디:
          <input type="text" name="userId" >
          <br><br>
          비밀번호:
          <input type="password" name="userPw">
          <br><br>
          <button type="submit">로그인</button>
          <button type="reset">다시쓰기</button>
       </form>
 
 </div>