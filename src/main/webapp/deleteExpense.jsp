<%--
  Created by IntelliJ IDEA.
  User: thelmawendy
  Date: 3/9/25
  Time: 1:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Expense</title>
</head>
<body>
<form action="DeleteExpense" method="post">
  <input type="hidden" name="expenseId" value="${expense.expenseId}">
  <p>Are you sure you want to delete this expense?</p>
  <button type="submit">Yes, Delete</button>
</form>

</body>
</html>
