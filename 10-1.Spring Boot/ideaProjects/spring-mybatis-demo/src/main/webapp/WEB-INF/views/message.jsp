<%@ page contentType ="text/html; charset =utf-8" pageEncoding ="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <mata charset = "utf-8">
        <title> Message </title>
    </head>
    <body>
        <script>
            alert('${msg}');
            location.href = '${loc}';
        </script>
    </body>
</html>