<%@ page contentType ="text/html; charset =utf-8" pageEncoding ="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <mata charset = "utf-8">
        <title> Hello JSP </title>
        <style>
            table {
            border : 1px solid #ddd;
            }
        </style>
    </head>
    <body>
        <h1> 게시물 보기 </h1>
        <table>
            <tr>
                <td> 글번호 </td>
                <td> <c:out value="${board.num}"/></td>
                <td> 작성일 </td>
                <td> <fmt:formatDate value="${board.wdate}" pattern="yy-MM-dd"/> </td>
            </tr>
            <tr>
                <td> 글쓴이 </td>
                <td> <c:out value="${board.userId}"/></td>
                <td> 조회수 </td>
                <td> <c:out value="${board.readnum}"/> </td>
            </tr>
            <tr>
                <td> 첨부파일 </td>
                <td colspan="3"> 첨부파일 기다려 </td>
            </tr>
            <tr>
                <td> 제목 </td>
                <td colspan="3"> <c:out value="${board.title}"/></td>
            </tr>
            <tr>
                <td> 글내용 </td>
                <td colspan="3"> <c:out value="${board.content}"/></td>
            </tr>
            <tr>
                <td colspan="4">
                    <a href ="#">글수정</a>

                    <a href ="#">글삭제</a>
                </td>
            </tr>
        </table>
    </body>
</html>