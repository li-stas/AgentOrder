<%--
  Created by IntelliJ IDEA.
  User: Pro
  Date: 16.04.2020
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Отчет продаж</title>
</head>

<body>
<table border="2">
    <tr>
        <c:forEach var = "cHead" items = "${aHead}">
            <td>${cHead}</td>
        </c:forEach>
    </tr>
</table>
</body>

</html>
