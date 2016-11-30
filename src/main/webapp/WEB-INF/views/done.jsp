<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>

    <title>Success</title>

</head>
<body>
<div class="${messageStyle}">
    <c:out value="${message}"/>
    <c:out value="${err}"/>
</div>
</body>
</html>
