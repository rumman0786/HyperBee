<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HyperBee::Buzz!</title>
</head>
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

    <form:form action="/buzz" method="POST" modelAttribute="newBuzz">
        <form:label path="message">Enter your message:</form:label>
        <form:input path="message"/>
        <input type="submit" value="Send"/>
    </form:form>

    <p><a href="/buzz/flagBuzz">Flag latest buzz</a></p>
    <p><a href="/buzz/deactivateBuzz">Delete latest buzz</a></p>
</body>
</html>
