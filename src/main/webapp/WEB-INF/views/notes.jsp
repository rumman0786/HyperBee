<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bashir
  Date: 11/22/16
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Notes</title>
</head>
<body>
<a href="/user/note/add">Add Note</a>

<p/>
<c:forEach items="${noteList}" var="item">
    <c:out value="${item.getTitle()}"/><br>
    <c:out value="${item.getDescription()}"/>
    <br>
    <br>
</c:forEach>
</body>
</html>
