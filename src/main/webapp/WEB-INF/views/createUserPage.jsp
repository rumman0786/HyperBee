<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title></title>
</head>
<body>
    <spring:form action="/user/createUser" method="post" modelAttribute="user">
        <label for="username">Username</label><br>
        <spring:input path="username" id="username"/><br>
        <label for="firstName">First Name</label><br>
        <spring:input path="firstName" id="firstName"/><br>
        <label for="lastName">Last Name</label><br>
        <spring:input path="lastName" id="lastName"/><br>
        <label for="email">Email</label><br>
        <spring:input path="email" id="email"/><br>
        <label for="password">Password</label><br>
        <spring:input path="password" id="password"/><br>
        <button type="submit">Submit</button>
    </spring:form>
    <a href="/profile">Create Profile</a>
</body>
</html>
