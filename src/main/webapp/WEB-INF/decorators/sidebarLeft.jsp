<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-sm-2 sidenav top-padding">
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
                <div class="panel-heading">Note Stats</div>
                <div class="panel-body">
                    Sticky notes: ${stickyCount} <br>
                    Remaining Reminders: ${reminderCount}
                </div>
            </div>

        </div>
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
