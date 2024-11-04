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
                        <li class = "title"> <a href="/board/view?num=<c:out value='${board.num}'/>">
                        <c:out value="${board.title}"/>
                        <!-- 1일 이내에 쓴 글이라면 New 이미지 붙여주기 -->
                        <c:if test = "${board.newImg < 1}">
                            <img src = "/images/new.png" style="width : 16px" >
                        </c:if>

                        <c:if test = "${board.fileName !=null}">
                            <img src = "/images/attach.png" style="width : 16px" >
                        </c:if>
                        </a> </li>

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
                <span> 총 게시글수 : ${totalCount}개 </span> &nbsp; &nbsp;
                <span> ${paging.pageNum} / ${paging.pageCount} pages </span>
            </div>
            <!-- 페이지 네비게이션------------------- -->
            <c:forEach var="i" begin="1" end="${paging.pageCount}">
                <a href = "list?pageNum=${i}" > [<c:out value="${i}"/>] </a>
            </c:forEach>
            <!-- -------------------------------- -->

        </div>
    </body>
</html>