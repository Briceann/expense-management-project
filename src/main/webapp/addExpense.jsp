<%--
  Created by IntelliJ IDEA.
  User: thelmawendy
  Date: 2/25/25
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Expense</title>
</head>
<body>
<form action="addExpense" method="post">
    Date: <input type="date" name="date" required>
    Category:
    <select name="category">
        <option>Food</option>
        <option>Rent</option>
        <option>Travel</option>
        <option>Shopping</option>
    </select>
    Amount: <input type="number" name="amount" required>
    Description: <input type="text" name="description">
    <button type="submit">Add Expense</button>
</form>

</body>
</html>
