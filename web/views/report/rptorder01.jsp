<%@ page import="java.util.List" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Pro
  Date: 16.04.2020
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Отчет продаж</title>
</head>
<body>
<div>
    <h1>Отчет продаж</h1>
</div>

<table border="2">
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
