<%@ page contentType ="text/html; charset =utf-8" pageEncoding ="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <mata charset = "utf-8">
        <title> Hello JSP </title>
    </head>
    <body>
        <h1> Hello JSP </h1>
        <h2> Hello Spring JPA Ajax </h2>
        <hr>
        <button id = "btn1">데이터 가져오기(json 데이터 1개-객체)</button>
        <button id = "btn2">데이터 가져오기(json 데이터 여러개-배열)</button>
        <hr color= "red">
        <div id = "result">

        </div>
    </body>
</html>