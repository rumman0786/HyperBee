<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>
<c:choose>
    <c:when test="${authUser.isAdmin()}">
        <h2>Select user whose activity log you want to view:</h2>
        <form:form action="/user/activity/log" method="post" modelAttribute="userInfo">
            <c:forEach items="${userInfo.userList}" var="user">
                <div class="input-group">
                        <span class="input-group-addon">
                            <form:radiobutton value="${user.id}" path="userId"/>
                        </span>
                    <label class="form-control"><c:out value="${user.getFirstName()} ${user.getLastName()} [${user.getUsername()}]"/></label>
                </div>
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
                    <li class="list-group-item list-group-item-warning"><c:out
                            value="${activity.getDateAndTime()}"/></li>
                    <li class="list-group-item"><c:out value="${activity.summary}"/></li>
                </c:forEach>
            </ul>
        </div>
    </c:otherwise>
</c:choose>
</body>


<%--<div class="col-lg-6">--%>
    <%--<div class="input-group">--%>
      <%--<span class="input-group-addon">--%>
        <%--<input type="radio" aria-label="...">--%>
      <%--</span>--%>
        <%--<input type="text" class="form-control" aria-label="...">--%>
    <%--</div>--%>
    <%--<!-- /input-group -->--%>
<%--</div>--%>
<%--<!-- /.col-lg-6 -->--%>