<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HypeerBee-Hive Page</title>
</head>
<body>

<h1>Welcome to ${hive.name}</h1>

<p>Description : ${hive.description}</p>
<img src="/images/image.jpg">

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
                    <form:checkbox path="userIdList" value="${user.id}" label="${user.username}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit">Add User</button>
</form:form>

<div>
    <form:form action="/user/hive/post/${hiveId}" method="POST" commandName="post">
        <div class="panel panel-success">
            <div class="panel-heading clearfix">
                <h4 class="panel-title pull-left" style="padding-top: 7.5px;">Add Post</h4>
            </div>
            <div class="panel-body">
                <form:input type="text" placeholder="Description" class="form-control" path="description"/>
            </div>
            <div class="panel-footer clearfix">
                <div class="btn-group pull-right">
                    <button class="btn btn-warning btn-sm" type="submit">Post</button>
                </div>
            </div>
        </div>
    </form:form>

    <c:forEach items="${postList}" var="item">
        <div class="panel panel-warning">
            <div class="panel-heading clearfix">
                <h4 class="panel-title pull-left" style="padding-top: 7.5px;"><strong>${item.user.username}</strong></h4>
            </div>
            <div class="panel-body">${item.description}</div>
            <div class="panel-footer clearfix">
                <div class="pull-right">
                      <fmt:formatDate type="date" value="${item.dateCreated.time}" pattern="dd-MM-yy"/>
                </div>
            </div></div>
    </c:forEach>

</div>


</body>
</html>
