<%--
  Created by IntelliJ IDEA.
  User: Pro
  Date: 08.04.2020
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>InfoConnect</title>
</head>

<body>
<div>
    <h1>InfoConnect</h1>
</div>

<div>
    <div>
      <%--  <div>
            <h2>Info_Connect</h2>
        </div>--%>
        <%
            List<String> aStr = (List<String>) request.getAttribute("aboutConnect");

            if (aStr != null && !aStr.isEmpty()) {
                out.println("<ui>");
                for (String s : aStr) {
                    out.println("<li>" + s + "</li>");
                }
                out.println("</ui>");
            } else out.println("<p>There are no users yet!</p>");
        %>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>