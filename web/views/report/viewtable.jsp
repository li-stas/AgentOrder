<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Pro
  Date: 16.04.2020
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
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
