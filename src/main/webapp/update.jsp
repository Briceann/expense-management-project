<%--
  Created by IntelliJ IDEA.
  User: thelmawendy
  Date: 3/9/25
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit/Update</title>
</head>
<body>
<form action="UpdateExpense" method="post">
    <input type="hidden" name="expenseId" value="${expense.expenseId}">
    Date: <input type="date" name="date" value="${expense.date}" required>
    Category: <select name="category">
    <option value="1" ${expense.category.categoryId == 1 ? "selected" : ""}>Food</option>
    <option value="2" ${expense.category.categoryId == 2 ? "selected" : ""}>Rent</option>
</select>
    Amount: <input type="number" name="amount" value="${expense.amount}" required>
    Description: <input type="text" name="description" value="${expense.description}">
    <button type="submit">Update Expense</button>
</form>

</body>
</html>
