<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: azim
  Date: 11/24/16
  Time: 1:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Welcome to ${hive.name}</h1>

<p>Description : ${hive.description}</p>

<p>Add people</p>
<form:form method="POST" action="/user/hive/insertuser/${hiveId}" commandName="userIdInfo">
    <table border="1">
        <thead>
        <tr>
            <th>All User</th>
        </tr>
        </thead>
        <c:forEach var="user" items="${userList}" varStatus="loop">
            <tr>
                <td>
                    <form:checkbox path="userIdList" value="${user.id}" label="${user.firstName} ${user.lastName}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit">Add User</button>
</form:form>

</body>
</html>
