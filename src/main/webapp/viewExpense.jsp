<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thelmawendy
  Date: 3/9/25
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Expense List</title>
</head>
<body>
<h2>Expense List</h2>
<a href="addExpense.jsp">Add New Expense</a>

<table border="1">
    <tr>
        <th>Date</th><th>Category</th>
        <th>Amount</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="expense" items="${expenses}">
        <tr>
            <td>${expense.date}</td>
            <td>${expense.category.name}</td>
            <td>${expense.amount}</td>
            <td>${expense.description}</td>
            <td>
                <a href="expense?action=edit&id=${expense.expenseId}">Edit</a>
                <a href="expense?action=delete&id=${expense.expenseId}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
