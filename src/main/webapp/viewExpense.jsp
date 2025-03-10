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
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Date</th><th>Category</th><th>Amount</th><th>Description</th><th>Actions</th>
    </tr>
    <c:forEach var="expense" items="${expenses}">
        <tr>
            <td>${expense.date}</td>
            <td>${expense.category.name}</td>
            <td>${expense.amount}</td>
            <td>${expense.description}</td>
            <td>
                <a href="updateExpense.jsp?expenseId=${expense.expenseId}">Edit</a>
                <a href="deleteExpense.jsp?expenseId=${expense.expenseId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
