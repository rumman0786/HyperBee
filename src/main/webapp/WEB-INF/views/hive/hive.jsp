<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: azim
  Date: 11/24/16
  Time: 11:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p>Hive List</p>
<c:forEach items="${hiveList}" var="item">
    <a href="/user/hive/show/${item.id}"> <c:out value="${item.name}"/></a><br>
    <br>
    <br>
</c:forEach>
<br>

<a href="/hive/create">Create Hive</a>

</body>
</html>
