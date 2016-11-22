<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: azim
  Date: 11/22/16
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HyperBee</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div>
    <form:form action="/hive/post" method="post" commandName="hive">
        <h2>Create Hive</h2>
        <form:input path="name" type="text" placeholder="Hive Name"/>
        <form:input path="description" type="text" placeholder="Hive Description"/>
        <form:input path="imagePath" type="file" width="48" height="48"/>
        <button type="submit">Create</button>
    </form:form>
</div>
</body>
</html>
