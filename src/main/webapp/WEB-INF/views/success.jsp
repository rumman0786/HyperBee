<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Success</title>
    <meta http-equiv="Refresh" content="1;url=${redirectUrl}">

</head>
<body>
    <c:out value="${message}"/>
</body>
</html>
