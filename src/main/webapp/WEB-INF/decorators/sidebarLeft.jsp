<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-sm-2 sidenav top-padding">
    <c:choose>
        <c:when test="${authUser.isAdmin()}">
            <div class="well">
                <h4>Total active users: <c:out value="${activeUsers}"/></h4>
                <h4>Total deactivated: <c:out value="${inactiveUsers}"/></h4>
            </div>
        </c:when>
    </c:choose>

    <div class="well">
        <c:choose>
            <c:when test="${authUser.isAdmin()}">
                <h4>Total active users: <c:out value="${statsMap['activeUsers']}"/></h4>
                <h4>Total deactivated: <c:out value="${statsMap['deactivatedUsers']}"/></h4>
            </c:when>
        </c:choose>
    </div>

    <div class="well">
        <div class="panel-success">
            <div class="panel panel-info">
                <div class="panel-heading"><fmt:message key="sidebar.left.note.label.stats"/></div>
                <div class="panel-body">
                    <fmt:message key="sidebar.left.note.stickyCount"/>${stickyCount} <br>
                    <fmt:message key="sidebar.left.note.reminderCount"/>${reminderCount}
                </div>
            </div>
        </div>
    </div>

    <div class="panel-success">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="sidebar.left.Buzz.title"/></div>
            <div class="panel-body">
                <h4><fmt:message key="sidebar.left.buzz.active"/></h4> <c:out value="${activeBuzz}"/>
                <c:if test="${authUser.isAdmin()}">
                    <h4><fmt:message key="sidebar.left.buzz.inactive"/></h4> <c:out value="${inactiveBuzz}"/>
                </c:if>
                <h4><fmt:message key="sidebar.left.buzz.flagged"/></h4> <c:out value="${flaggedBuzz}"/>
                <h4><fmt:message key="sidebar.left.buzz.pinned"/></h4> <c:out value="${pinnedBuzz}"/>
            </div>
        </div>
    </div>
</div>
</html>
