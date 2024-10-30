<%@ page contentType ="text/html; charset =utf-8" pageEncoding ="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <mata charset = "utf-8">
        <title> main </title>
    </head>
    <body>
        <h1> Main Page</h1>
        <h2> ${sessionScope.loginUser.name}</h2>
    </body>
</html>