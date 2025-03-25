<%@include file="head.jsp"%>
<html>
<body>
<h2>Search Users</h2>
<form action="${pageContext.request.contextPath}/user" method="get">
    <div class="form-group">
        <label for="searchTerm">Search by Last Name:</label>
        <input type="text" id="searchTerm" name="searchTerm" placeholder="Enter last name">
    </div>
    <div>
        <button type="submit" name="submit" value="search" class="btn btn-primary">Search</button>
        <button type="submit" name="submit" value="search" class="btn btn-primary">View All Users</button>
    </div>
</form>
</body>
</html>