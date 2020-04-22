<%--
  Created by IntelliJ IDEA.
  User: Pro
  Date: 16.04.2020
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Отчет продаж продукции</title>
</head>
<body>
<div>
    <h1>Отчет продаж продукции (${currentPage}/${noOfPages})</h1>
</div>

<%@include file="viewtable.jsp"%>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="rpttov02.do?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="rpttov02.do?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="rpttov02.do?page=${currentPage + 1}">Next</a></td>
</c:if>


<div>
    <button onclick="location.href='../..'">Back to main</button>
</div>

</body>

</html>