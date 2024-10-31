<%@ page contentType ="text/html; charset =utf-8" pageEncoding ="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
    el표현식을 이용한 쿠키 출력
    ${cookie}==> Map 객체
    ${cookie.키이름} ==>cookie 객체
    ${cookie.키이름.value} ==> String객체. 쿠키에 저장한 value값

    ${cookie.uid.value}==> 쿠키에 저장한 아이디값
--%>


<div class = "login-wrap" >
    <h2> Login </h2>
    <form name = "loginF" action = "login" method = "post">
        <table>
            <tr>
                <td> 아이디 </td>
                <td>
                    <input type = "text" name="userId"
                    value ="${cookie.uid.value}"
                    id ="userId" placeholder = "ID" required>
                </td>
            </tr>
            <tr>
                <td> 비밀번호 </td>
                <td>
                    <input type = "password" name = "userPw"
                    id = "userPw" placeholder = "Password" required>
                </td>
            </tr>
            <tr>
                <td colspan="2" style = "width:100%; padding:10px; text-align:center;">
                    <label for="saveId">
                        <input type ="checkbox" name = "saveId"
                        <c:if test= "${cookie.uid !=null}">
                            checked
                        </c:if>
                        id="saveId">
                        아이디 저장
                    </label>
                    <button type="submit"> Login </button>
                </td>
            </tr>
        </table>
    </form>
</div>