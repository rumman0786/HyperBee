<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>
    <div>
        <spring:form action="/user/login" method="post" modelAttribute="user">
            <label><spring:input path="username" />Enter username</label><br>
            <label><spring:input path="password" />Enter password</label><br>
            <button type="submit">Submit</button>
        </spring:form>
    </div>
</body>
</html>
