<%@ page import="edu.matc.entity.Expense" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: thelmawendy
  Date: 2/25/25
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    List<Expense> expenses = (List<Expense>) session.getAttribute("expenses");
    if (expenses == null) {
        expenses = new ArrayList<>();
    }
%>

<html>
<head>
    <title>Dashboard</title>
    <a href="addExpense.jsp">Add Expense</a>
</head>
<body>
    <h2>Expense Dashboard</h2>

    <table>
        <tr><th>Date</th><th>Category</th><th>Amount</th><th>Description</th></tr>
        <% for (Expense exp : expenses) { %>
        <tr>
            <td><%= exp.getDate() %></td>
            <td><%= exp.getCategory() %></td>
            <td><%= exp.getAmount() %></td>
            <td><%= exp.getDescription() %></td>
        </tr>
        <% } %>
    </table>

</body>
</html>
