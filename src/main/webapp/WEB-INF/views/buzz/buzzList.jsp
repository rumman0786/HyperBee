<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="pre-scrollable">
    <table class="table table-striped table-bordered">
        <c:forEach items="${pinnedBuzzList}" var="buzz">
            <c:choose>
                <c:when test="${buzz.isFlagged()}">
                    <tr class="info">
                        <td>
                            <c:if test="${authUser.isAdmin()}">
                                <a href="/buzz/flagBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-flag"/></a>
                                <a href="/buzz/deactivateBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-remove"/></a>
                            </c:if>
                            <font color="#191970"><i>The message has been flagged for it's content.</i></font>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr class="info">
                        <td>
                            <c:if test="${authUser.isAdmin()}">
                                <a href="/buzz/flagBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-flag"/></a>
                                <a href="/buzz/deactivateBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-remove"/></a>
                                <a href="/buzz/pinBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-pushpin"/></a>
                            </c:if>
                            <i><font color="#191970">[PINNED] </font></i>
                            <c:out value="${buzz.user.username} [${buzz.buzzTime.getTime()}]: ${buzz.message}"/>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:forEach items="${buzzList}" var="buzz">
            <c:choose>
                <c:when test="${buzz.isFlagged()}">
                    <tr>
                        <td>
                            <c:if test="${authUser.isAdmin()}">
                                <a href="/buzz/flagBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-flag"/></a>
                                <a href="/buzz/deactivateBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-remove"/></a>
                            </c:if>
                            <font color="#191970"><i>The message has been flagged for it's content.</i></font>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <c:if test="${authUser.isAdmin()}">
                                <a href="/buzz/flagBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-flag"/></a>
                                <a href="/buzz/deactivateBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-remove"/></a>
                                <a href="/buzz/pinBuzz?id=${buzz.id}"><span class="glyphicon glyphicon-pushpin"/></a>
                            </c:if>
                            <c:out value="${buzz.user.username} [${buzz.buzzTime.getTime()}]: ${buzz.message}"/>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>
</div>
</body>
</html>