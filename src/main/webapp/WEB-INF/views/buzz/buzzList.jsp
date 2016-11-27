<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<table class="table table-striped table-bordered">

<c:forEach items="${buzzList}" var="buzz">
    <c:choose>
        <c:when test="${buzz.isFlagged()}">
            <tr><td>
                <a href="/buzz/flagBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-flag"/></a>
                <a href="/buzz/deactivateBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-remove"/></a>
                <c:out value="The message has been flagged for it's content."/>
            </td></tr>
        </c:when>
        <c:otherwise>
            <tr><td>
                <a href="/buzz/flagBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-flag"/></a>
                <a href="/buzz/deactivateBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-remove"/></a>
                <c:out value="${buzz.user.username}[${buzz.buzzTime.getTime()}]: ${buzz.message}"/>
            </td></tr>
        </c:otherwise>
    </c:choose>
</c:forEach>
</table>
</body>
</html>
