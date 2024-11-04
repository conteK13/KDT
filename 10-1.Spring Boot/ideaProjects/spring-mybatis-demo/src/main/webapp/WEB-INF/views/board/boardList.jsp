<%@ page contentType ="text/html; charset =utf-8" pageEncoding ="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <mata charset = "utf-8">
        <title> 글 목록 </title>
    </head>
    <body>
        <div class="wrap">
            <h1> Spring Board List </h1>

            <!-- 검색 form--------------------- -->
            <!-- ----------------------------- -->
            <br>
            <ul class = "boardList">
                <li> 글번호 </li>
                <li> 글제목 </li>
                <li> 작성자 </li>
                <li> 작성일 </li>
                <li> 조회수 </li>
                <!-- ------------------ -->
                <c:if test= "${boardList==null || empty boardList}">
                    <li style = "width:100%"> 데이터가 존재하지 않습니다. </li>
                </c:if>
                <c:if test ="${boardList != null && not empty boardList}">
                    <c:forEach var = "board" items = "${boardList}">
                        <li> <c:out value="${board.num}"/> </li>
                        <li> <a href="/board/view?num=<c:out value='${board.num}'/>"> <c:out value="${board.title}"/> </a> </li>
                        <li> <c:out value="${board.userId}"/> </li>
                        <li>
                            <fmt:formatDate value="${board.wdate}" pattern="yy-MM-dd"/>
                            <%--
                                yy 년도 / MM 월 / dd 일
                                HH 시간(24시간) / mm 분 / ss 초
                            --%>
                        </li>
                        <li> <c:out value="${board.readnum}"/> </li>
                    </c:forEach>
                </c:if>
                <!-- ------------------ -->
            </ul>
            <div class="clear"></div>
            <div class = "divTotal">
                <span> 총 게시글수 : ${totalCount} </span>
                <span> 1 / 3 pages </span>
            </div>
            <!-- 페이지 네비게이션------------------- -->
            <!-- -------------------------------- -->

        </div>
    </body>
</html>