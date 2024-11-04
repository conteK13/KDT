<%@ page contentType ="text/html; charset =utf-8" pageEncoding ="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <mata charset = "utf-8">
        <title> Hello JSP </title>
        <style>
            h1, h2, h3 {
                text-align:center;
            }

        </style>
    </head>
    <body>
        <h1> Spring Board 글내용 </h1>
        <div style="text-align:center">
            <a href ="/board/form"> 글쓰기 </a>
            <a href ="/board/list"> 글목록 </a>
        </div>
        <div id = "boardView">
            <c:choose>
                <c:when test = "${board==null}">
                    <h3> 해당 게시글을 없습니다 </h3>
                </c:when>
                <c:otherwise>
                    <table border="1">
                        <tr>
                            <td width = "20%"> <b>글번호</b> </td>
                            <td width = "30%"> <c:out value="${board.num}"/></td>
                            <td width = "20%"> <b>작성일</b> </td>
                            <td width = "30%"> <fmt:formatDate value="${board.wdate}" pattern="yy-MM-dd"/> </td>
                        </tr>
                        <tr>
                            <td> <b>글쓴이</b> </td>
                            <td> <c:out value="${board.userId}"/></td>
                            <td> <b>조회수</b> </td>
                            <td> <c:out value="${board.readnum}"/> </td>
                        </tr>
                        <tr>
                            <td> <b>첨부파일</b> </td>
                            <td colspan="3">
                                <a href ="/board_upload/${board.fileName}" download>
                                    <c:out value="${board.originFilename}"/>
                                </a>
                                [<c:out value = "${board.fileSize}"/> bytes]
                            </td>
                        </tr>
                        <tr>
                            <td> <b>제 목</b> </td>
                            <td colspan="3"> <c:out value="${board.title}"/></td>

                        </tr>
                        <tr>
                            <td> <b>글내용</b> </td>
                            <td colspan="3" id = "content">
                                ${board.content}
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="text-align:center">
                                <a href ="/board/form">글쓰기</a> | <a href ="/board/list">글목록</a>
                                <!-- 로그인한 유저가 글을 쓴 사람이라면 -->
                                <c:if test="${loginUser.userId == board.userId}">
                                     |
                                    <a href="javascript:goEdit()">글수정</a> |
                                    <a href ="javascript:goDel()">글삭제</a>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </c:otherwise>
            </c:choose>
            <!-- 수정 또는 삭제 form------------------------- -->
            <form id = "frm">
                <input type = "hidden" name ="num"
                value="<c:out value='${board.num}'/>">
                <!-- 수정 또는 삭제 할 글 번호 : hidden data -->
                <div id = "divPasswd">
                    <label for = "passwd"> 비밀번호 </label>
                    <input type="password" name = "passwd" id = "passwd"
                    placeholder = "Password" required>
                    <button id = "btn1"></button>
                </div>
            </form>
            <!-- ----------------------------------------- -->
        </div>

        <script>
            const goEdit= function(){
                const frm = document.getElementById("frm");
                frm.action="/user/edit";
                frm.method= "post";

                //비밀번호 입력 폼 보여주기
                const div = document.getElementById("divPasswd");
                div.style.display = "block";

                const btn1 = document.getElementById('btn1');
                btn1.innerText="글수정";
            }

            const goDel = function(){
                let yn = confirm('게시글을 삭제 할까요?');
                if(!yn) return;

                const frm = document.getElementById("frm");
                frm.action="/user/delete";
                frm.method= "post";

                //비밀번호 입력 폼 보여주기
                const div = document.getElementById("divPasswd");
                div.style.display = "block";
                // 버튼 찾기
                const btn1 = document.getElementById('btn1');
                btn1.innerText="글삭제";

                frm.submit;
            }

        </script>
    </body>
</html>