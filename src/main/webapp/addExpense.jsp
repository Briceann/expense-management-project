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
<h2>Add Expense</h2>
<form action="AddExpense" method="post">
    <label>Date:</label>
    <input type="date" name="date" required><br><br>

    <label>Category:</label>
    <select name="category">
        <option>Food</option>
        <option>Rent</option>
        <option>Travel</option>
        <option>Shopping</option>
    </select><br><br>

    <label>Amount:</label>
    <input type="number" name="amount" required><br><br>

    <label>Description:</label>
    <input type="text" name="description"><br><br>

    <button type="submit">Add Expense</button>
</form>

</body>
</html>
