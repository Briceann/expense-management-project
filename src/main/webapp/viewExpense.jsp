<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: thelmawendy
  Date: 3/9/25
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" class="init">
    $(document).ready(function () {
        $('#expenseTable').DataTable();
    } );
</script>
<html>
<head>
    <title>View Expenses</title>
</head>
<body>
<div class="container-fluid">
    <%
        Integer userId = (Integer) request.getAttribute("userId");
    %>
    <h2>
        <%= (userId != null) ? "Expenses for User ID: " + userId : "All Expenses" %>
    </h2>
    <a href="addExpense.jsp">Add New Expense</a>

    <table id="expenseTable" class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>Date</th>
            <th>Category</th>
            <th>Amount</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
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
        </tbody>
    </table>
</div>
<a href="index.jsp">Return to Home </a>
</body>
</html>
