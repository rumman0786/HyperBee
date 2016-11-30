<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <c:choose>
        <c:when test="${authUser.isAdmin()}">
            <h2>Select user whose activity log you want to view:</h2>
            <form:form action="/user/activity/log" method="post" modelAttribute="userInfo">
                <c:forEach items="${userInfo.userList}" var="user">
                    <form:radiobutton value="${user.id}" path="userId" label="${user.username}"/>
                    <br>
                </c:forEach>
                <button type="submit">Submit</button>
            </form:form>
        </c:when>
        <c:otherwise>
            <h2>Here are your activities: </h2>
            <div class="pre-scrollable set-activity-height">
                <ul class="list-group">
                    <c:forEach items="${activityList}" var="activity">
                        <li class="list-group-item list-group-item-warning"><c:out value="${activity.getDateAndTime()}"/></li>
                        <li class="list-group-item"><c:out value="${activity.summary}"/></li>
                    </c:forEach>
                </ul>
            </div>
        </c:otherwise>
    </c:choose>
>>>>>>> feature/hyperbee-rayed
</body>
