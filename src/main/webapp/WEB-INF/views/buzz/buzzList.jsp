<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<c:forEach items="${buzzList}" var="buzz">
    <c:choose>
        <c:when test="${buzz.isFlagged()}">
            <p><c:out value="The message has been flagged for it's content."/></p>
        </c:when>
        <c:otherwise>
            <p><c:out value="${buzz.user.username}[${buzz.buzzTime.getTime()}]: ${buzz.message}"/></p>
        </c:otherwise>
    </c:choose>
</c:forEach>
</body>
</html>
