<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-sm-2 sidenav top-padding">
    <div class="well">
        <c:choose>
            <c:when test="${authUser.isAdmin()}">
                <h4>Total active users: <c:out  value="${statsMap['activeUsers']}"/></h4>
                <h4>Total deactivated: <c:out  value="${statsMap['deactivatedUsers']}"/></h4>
            </c:when>
        </c:choose>
    </div>

    <div class="well">
        <c:choose>
            <c:when test="${authUser.isAdmin()}">
                <h4><fmt:message key="sidebar.left.buzz.active"/></h4> ${buzzStats.get(0)}
                <h4><fmt:message key="sidebar.left.buzz.inactive"/></h4> ${buzzStats.get(1)}
                <h4><fmt:message key="sidebar.left.buzz.flagged"/></h4> ${buzzStats.get(2)}
                <h4><fmt:message key="sidebar.left.buzz.pinned"/></h4> ${buzzStats.get(3)}
            </c:when>

            <c:otherwise>
                <h4><fmt:message key="sidebar.left.buzz.active"/></h4> ${buzzStats.get(0)}
                <h4><fmt:message key="sidebar.left.buzz.flagged"/></h4> ${buzzStats.get(1)}
                <h4><fmt:message key="sidebar.left.buzz.pinned"/></h4> ${buzzStats.get(2)}
            </c:otherwise>
        </c:choose>
    </div>
    <div class="well">
        counter
    </div>
    <div class="well">
        counter
    </div>
    <div class="well">
        counter
    </div>
    <div class="well">
        counter
    </div>
</div>
</html>
