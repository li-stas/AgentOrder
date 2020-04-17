<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Pro
  Date: 16.04.2020
  Time: 13:18
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
    <h1>Отчет продаж продукции</h1>
</div>

<table border="2">
<%--<table border="1" cellpadding="5" cellspacing="5">--%>

    <%
        List<String> aHead = (List<String>) request.getAttribute("aHead");
        List<List<String>> aRecList = (List<List<String>>) request.getAttribute("aRecList");


        out.println("<tr>");
        for (String cStr :  aHead) {
            out.println("<td>" + cStr + "</td>");
        }
        out.println("</tr>");

        if (aRecList != null && !aRecList.isEmpty()) {
            for (List<String> aRec : aRecList) {
                out.println("<tr>");
                for (String cStr : aRec) {
                    out.println("<td>" + cStr + "</td>");
                }
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td>There are no users yet!</td></tr>");
        }
    %>
    </tr>
</table>
<div>
    <button onclick="location.href='../..'">Back to main</button>
</div>

</body>

</html>