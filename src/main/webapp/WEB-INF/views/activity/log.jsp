<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<div class="container">
    <h2>Activity Log</h2>
    <ul class="list-group">
        <c:forEach items="${activityList}" var="activity">
            <li class="list-group-item list-group-item-warning"><c:out value="${activity.getDateAndTime()}"/></li>
            <li class="list-group-item"><c:out value="${activity.summary}"/></li>
        </c:forEach>
    </ul>
</div>
</body>
