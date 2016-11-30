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
        <h4><fmt:message key="sidebar.left.buzz.active"/></h4> <c:out value="${activeBuzz}"/>
        <c:if test="${authUser.isAdmin()}">

            <h4><fmt:message key="sidebar.left.buzz.inactive"/></h4> <c:out value="${inactiveBuzz}"/>
        </c:if>
        <h4><fmt:message key="sidebar.left.buzz.flagged"/></h4> <c:out value="${flaggedBuzz}"/>
        <h4><fmt:message key="sidebar.left.buzz.pinned"/></h4> <c:out value="${pinnedBuzz}"/>
    </div>
</div>
</html>
