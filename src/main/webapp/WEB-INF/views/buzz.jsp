<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HyperBee::Buzz!</title>
</head>
<body>
    <c:forEach items="${buzzList}" var="buzz">
        <p><c:out value="${buzz.user.username}[${buzz.buzzTime.getTime()}]: ${buzz.message}"/></p>
    </c:forEach>

    <form:form action="/buzz" method="POST" modelAttribute="newBuzz">
        <form:label path="message">"Enter your message:</form:label>
        <form:input path="message"/>
        <input type="submit" value="Send"/>
    </form:form>
</body>
</html>
