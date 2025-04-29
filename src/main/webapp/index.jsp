<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expense Management</title>
</head>
<body>


<c:choose>
    <c:when test="${empty userName}">
        <a href = "logIn">Log in</a>
    </c:when>
    <c:otherwise>
        <h3>Welcome ${userName}</h3>
    </c:otherwise>
</c:choose>

    <div class="container">
        <h1>Welcome to Expense Tracker</h1>
        <p>Manage your expenses, view and generate expense reports</p>
        <div class="links">
            <a href="viewExpense.jsp" class="btn">View Expense</a>
            <a href="addExpense.jsp" class="btn">Add Expense</a>
            <a href="expense">View All Expenses</a>
            <a href="expense?userId=1">View My Expenses</a>


        </div>
    </div>
</body>
</html>