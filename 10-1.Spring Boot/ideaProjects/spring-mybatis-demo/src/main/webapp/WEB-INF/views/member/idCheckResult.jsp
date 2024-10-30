<%@ page contentType ="text/html; charset =utf-8" pageEncoding ="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .header, .footer{
        display:none;
    }
    #userId {
        padding : 4px;
        border = 1px solid #ddd;
    }
</style>
<script>
    const check = function(){
        if(!idf.userId.value ){
            alert("아이디를 입력하세요");
            idf.userId.focus();
            return false;
        }//if----
        return true;
    }//----------------

    const setId = function(uid){
        // 부모형의 userId input 텍스트에 uid 값을 설정하자.
        // window>document>form>input, select, textarea,...
        // opener <= 부모창(window)
        opener.document.forms[0].userId.value=uid;
        self.close();
    }//-------------------
</script>

<div class = "wrap">
    <div id = "idResult">
        <h3 style="color:red"> ${msg} </h3>
        <c:if test="${result=='ok'}">
            <button class = "btn"
            onclick = "setId('${userId}')"> 아이디 사용하기 </button>
        </c:if>
    </div>
    <br>
    <form name = "idf" action = "idCheck" method = "post"
    onsubmit = "return check()">
        <label for = "userId">
            아이디
        </label>
        <input type="text" name="userId" id = "userId"
        placeholder="ID" autofocus="autofocus">
        <button> 확  인 </button>

    </form>
</div>