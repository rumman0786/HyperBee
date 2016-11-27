<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stalk Therap</title>
</head>
<body>
<form action="/search" method="post">
    <input type="text" name="search" value="search">
    <button type="submit">Search</button>
    <br>
    <a href="/stalk/profile/${user.username}"><c:out value="${user.firstName} ${user.lastName}"/>
</form>
<br>
${message}
</body>
</html>
