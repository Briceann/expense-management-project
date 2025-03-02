<%--
  Created by IntelliJ IDEA.
  User: thelmawendy
  Date: 2/25/25
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Account</title>
</head>
<body>
    <form action="register" method="post">
        Name: <input type="text" name="name" required>
        Email: <input type="email" name="email" required>
        Password: <input type="password" name="password" required>
        <button type="submit">Register</button>
    </form>

</body>
</html>
