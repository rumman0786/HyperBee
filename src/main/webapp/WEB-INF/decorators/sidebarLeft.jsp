<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-sm-2 sidenav top-padding">
    <c:if test="${authUser.isAdmin()}">
        <div class="panel-success">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <fmt:message key="sidebar.left.user.title"/>
                </div>
                <div class="panel-body">
                    <h4><fmt:message key="sidebar.left.user.active"/></h4>
                    <h4><c:out value="${activeUsers}"/></h4>
                    <h4><fmt:message key="sidebar.left.user.inactive"/></h4>
                    <h4><c:out value="${inactiveUsers}"/></h4>
                </div>
            </div>
        </div>
    </c:if>

    <div class="panel-success">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="sidebar.left.note.label.stats"/></div>
            <div class="panel-body">
                <a href="/note/view/sticky"><fmt:message key="sidebar.left.note.stickyCount"/> ${stickyCount}</a> <br>
                <fmt:message key="sidebar.left.note.reminderCount"/> ${reminderCount}<br>
                <a href="/note/view/reminder"><fmt:message key="sidebar.left.note.reminderCountToday"/>
                 ${remindCountToday}
                </a><br>
                <fmt:message key="sidebar.left.note.reminderCountNextWeek"/> ${remindCountNextWeek}<br>
            </div>
        </div>
    </div>

    <div class="panel-success">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="sidebar.left.buzz.title"/></div>
            <div class="panel-body">
                <strong><fmt:message key="sidebar.left.buzz.active"/></strong> <c:out value="${activeBuzz}"/><br>
                <c:if test="${authUser.isAdmin()}">
                    <strong><fmt:message key="sidebar.left.buzz.inactive"/></strong> <c:out value="${inactiveBuzz}"/><br>
                </c:if>
                <strong><fmt:message key="sidebar.left.buzz.flagged"/></strong> <c:out value="${flaggedBuzz}"/><br>
                <strong><fmt:message key="sidebar.left.buzz.pinned"/></strong> <c:out value="${pinnedBuzz}"/><br>
            </div>
        </div>
    </div>
</div>
</html>
