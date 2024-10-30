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
    }
</script>

<div class = "wrap">
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