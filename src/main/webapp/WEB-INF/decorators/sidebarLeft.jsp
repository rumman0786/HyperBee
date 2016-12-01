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
                    <fmt:message key="sidebar.left.user.active"/> <c:out value="${activeUsers}"/>
                    <br>
                    <fmt:message key="sidebar.left.user.inactive"/> <c:out value="${inactiveUsers}"/>
                </div>
            </div>
        </div>
    </c:if>

    <div class="panel-success">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="sidebar.left.note.label.stats"/></div>
            <div class="panel-body">
                <fmt:message key="sidebar.left.note.stickyCount"/>${stickyCount} <br>
                <fmt:message key="sidebar.left.note.reminderCount"/>${reminderCount}
            </div>
        </div>
    </div>

    <div class="panel-success">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="sidebar.left.buzz.title"/></div>
            <div class="panel-body">
                <fmt:message key="sidebar.left.buzz.active"/> <c:out value="${activeBuzz}"/><br>
                <c:if test="${authUser.isAdmin()}">
                    <fmt:message key="sidebar.left.buzz.inactive"/> <c:out value="${inactiveBuzz}"/><br>
                </c:if>
                <fmt:message key="sidebar.left.buzz.flagged"/> <c:out value="${flaggedBuzz}"/><br>
                <fmt:message key="sidebar.left.buzz.pinned"/> <c:out value="${pinnedBuzz}"/><br>
            </div>
        </div>
    </div>
</div>
</html>
