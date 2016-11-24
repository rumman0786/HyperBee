<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>

<html>
<head>

    <title></title>
</head>
<body>
<form:form action="/user/note/save" method="post" commandName="noteCommand">
    <label for="title">Title</label><br>
    <form:input path="title" id="title"/><br>
    <label for="description">Description</label><br>
    <form:input path="description" id="description"/><br>
    <button type="submit">Submit</button>
</form:form>
</body>
</html>
