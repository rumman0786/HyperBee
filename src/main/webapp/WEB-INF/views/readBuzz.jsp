<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
    <c:forEach items="${buzzList}" var="buzz">
        <p><c:out value="${buzz.user.username}[${buzz.buzzTime.getTime()}]: ${buzz.message}"/></p>
    </c:forEach>
</body>
</html>
