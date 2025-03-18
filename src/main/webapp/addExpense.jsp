<%--
  Created by IntelliJ IDEA.
  User: thelmawendy
  Date: 2/25/25
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="edu.matc.entity.Expense" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add New Expense</title>
</head>
<body>
<h2>${expense != null ? "Edit Expense" : "Add New Expense"}</h2>
<form action="expense" method="post">
    <input type="hidden" name="id" value="${expense != null ? expense.expenseId : ''}">
    <label>Date:</label>
    <input type="date" name="date" required value="${expense != null ? expense.date : ''}"><br>
    <label>Category ID:</label>
    <input type="number" name="categoryId" required value="${expense != null ? expense.category.categoryId : ''}"><br>
    <label>Amount:</label>
    <input type="text" name="amount" required value="${expense != null ? expense.amount : ''}"><br>
    <label>Description:</label>
    <input type="text" name="description" required value="${expense != null ? expense.description : ''}"><br>
    <input type="hidden" name="action" value="${expense != null ? 'update' : 'add'}">
    <button type="submit">Submit</button>
</form>
</body>
</html>